package ru.job4j.grabber;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import ru.job4j.grabber.utils.HabrCareerDateTimeParser;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

public class Grabber implements Grab {
    private Parse parse;
    private Store store;
    private Scheduler scheduler;
    private int time;

    public Grabber(Parse parse, Store store, Scheduler scheduler, int time) {
        this.parse = parse;
        this.store = store;
        this.scheduler = scheduler;
        this.time = time;
    }

    public Grabber() {

    }

    @Override
    public void init() throws SchedulerException {
        JobDataMap data = new JobDataMap();
        data.put("store", store);
        data.put("parse", parse);
        JobDetail job = newJob(GrabJob.class)
                .usingJobData(data)
                .build();
        SimpleScheduleBuilder times = simpleSchedule()
                .withIntervalInSeconds(time)
                .repeatForever();
        Trigger trigger = newTrigger()
                .startNow()
                .withSchedule(times)
                .build();
        scheduler.scheduleJob(job, trigger);
    }

    public static class GrabJob implements Job {

        @Override
        public void execute(JobExecutionContext context) {
            JobDataMap map = context.getJobDetail().getJobDataMap();
            Store store = (Store) map.get("store");
            Parse parse = (Parse) map.get("parse");
            List<Post> posts = parse.list("https://career.habr.com/vacancies/java_developer?page=");
            for (Post post : posts) {
                store.save(post);
            }
        }
    }

    public void web(Store store) {
        new Thread(() -> {
            try (ServerSocket server = new ServerSocket(Integer.parseInt(getProperties().getProperty("port")))) {
                while (!server.isClosed()) {
                    Socket socket = server.accept();
                    try (OutputStream out = socket.getOutputStream()) {
                        out.write("HTTP/1.1 200 OK\r\n\r\n".getBytes());
                        for (Post post : store.getAll()) {
                            out.write(post.toString().getBytes());
                            out.write(System.lineSeparator().getBytes());
                        }
                    } catch (IOException io) {
                        io.printStackTrace();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

        public static Properties getProperties() {
            Properties config = new Properties();
            try (InputStream in = Grabber.class.getClassLoader().getResourceAsStream("app.properties")) {
                config.load(in);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return config;
        }


    public static void main(String[] args) throws Exception {
        Grabber grab = new Grabber();
        getProperties();

        //Scheduler scheduler = grab.scheduler();
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        scheduler.start();

        //Store store = grab.store();
        var store = new PsqlStore(getProperties());

        var parse = new HabrCareerParse(new HabrCareerDateTimeParser());

        var time = Integer.parseInt(getProperties().getProperty("time"));

        //grab.init(new HabrCareerParse(new HabrCareerDateTimeParser()), store, scheduler);
        new Grabber(parse, store, scheduler, time).init();
        grab.web(store);
    }
}