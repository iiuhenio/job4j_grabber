package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

/**
 * В проекте агрегатор будет использоваться база данных. Открыть и закрывать соединение с базой накладно.
 * Чтобы этого избежать коннект к базе будет создаваться при старте. Объект коннект будет передаваться в Job.
 * Quartz создает объект Job, каждый раз при выполнении работы.
 * Давай покажем это на примере.
 */
public class AlertRabbit2 {

    public static Properties getProperties() {
        Properties config = new Properties();
        try (InputStream in = AlertRabbit.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Properties config = getProperties(); /* При старте приложения создайте connect к базе и передайте его в Job.*/
        Class.forName(config.getProperty("driver"));
        Connection connection = DriverManager.getConnection(
                config.getProperty("url"),
                config.getProperty("login"),
                config.getProperty("password")
        );

        try {
            List<Long> store = new ArrayList<>();
            /*
             * В объект Scheduler мы будем добавлять задачи, которые хотим выполнять периодически.
             */
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            /*
             * При создании Job мы указываем параметры data. В них мы передаем ссылку на store.
             * В нашем примере store это ArrayList.
             */
            JobDataMap data = new JobDataMap();
            data.put("connection", connection);
            /*
             *  создание задачи
             */
            JobDetail job = newJob(Rabbit.class)
                    .usingJobData(data)
                    .build();
            /*
             * создание расписания
             */
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(Integer.parseInt(config.getProperty("rabbit.interval")))
                    .repeatForever();
            /*
             * здесь указываем, когда начать запуск
             */
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
            /*
             * После выполнения работы в списке будут две даты.
             * Объект store является общим для каждой работы.
             */
            Thread.sleep(10000);
            scheduler.shutdown();
            System.out.println(store);
        } catch (Exception se) {
            se.printStackTrace();
        }
    }

    public static class Rabbit implements Job {

        public Rabbit() {
            System.out.println(hashCode());
        }
        /*
         * Чтобы получить объекты из context используется следующий вызов.
         */
        @Override
        public void execute(JobExecutionContext context) {
            System.out.println("Rabbit runs here ...");
            Connection cn = (Connection) context.getJobDetail().getJobDataMap().get("connection");
            try (PreparedStatement statement =
                         cn.prepareStatement("insert into rabbit (created_data) values (now());")) {
                statement.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}