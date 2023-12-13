//package ru.job4j.quartz;
        //
        //import org.quartz.*;
//import org.quartz.impl.StdSchedulerFactory;
//import java.io.*;
//import java.sql.*;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Properties;
//import static org.quartz.JobBuilder.*;
//import static org.quartz.TriggerBuilder.*;
//import static org.quartz.SimpleScheduleBuilder.*;
//
        //public class AlertRabbit {
    //
            //    public static Connection initConnection() throws Exception {
        //        Class.forName(getProperties().getProperty("driver"));
        //        String url = getProperties().getProperty("url");
        //        String login = getProperties().getProperty("login");
        //        String password = getProperties().getProperty("password");
        //        return DriverManager.getConnection(url, login, password);
        //    }
    //
            //    public static void main(String[] args) throws Exception {
        //
        //            List<Long> store = new ArrayList<>();
        //            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        //            scheduler.start();
        //            JobDataMap data = new JobDataMap();
        //            data.put("connection", initConnection());
        //
        //            JobDetail job = newJob(Rabbit.class)
                //                    .usingJobData(data)
                //                    .build();
        //            SimpleScheduleBuilder times = simpleSchedule()
                //                    .withIntervalInSeconds(Integer.parseInt(AlertRabbit.getProperties().getProperty("rabbit.interval")))
                //                    .repeatForever();
        //            Trigger trigger = newTrigger()
                //                    .startNow()
                //                    .withSchedule(times)
                //                    .build();
        //            scheduler.scheduleJob(job, trigger);
        //            Thread.sleep(5000);
        //            scheduler.shutdown();
        //            System.out.println(store);
        //    }
    //
            //    /**
     //     * (2)
     //     * quartz каждый раз создает объект с типом org.quartz.Job.
     //     * Вам нужно создать класс реализующий этот интерфейс.
     //     * Внутри этого класса нужно описать требуемые действия.
     //     * В нашем случае - это вывод на консоль текста.
     //     */
            //    public static class Rabbit implements Job {
        //
                //        public Rabbit() {
            //            System.out.println(hashCode());
            //        }
        //
                //        @Override
                //        public void execute(JobExecutionContext context) {
            //            System.out.println("Rabbit runs here ...");
            //            //List<Long> store = (List<Long>) context.getJobDetail().getJobDataMap().get("store");
            //            long l = System.currentTimeMillis();
            //            String sql = "INSERT INTO rabbit (created_date) VALUES (?)";
            //            Connection connection = (Connection) context.getJobDetail().getJobDataMap().get("connection");
            //            try {
                //                PreparedStatement preparedStatement = connection.prepareStatement(sql);
                //                preparedStatement.setLong(1, l);
                //                preparedStatement.execute();
                //            } catch (Exception e) {
                //                throw new RuntimeException(e);
                //            }
            //        }
        //    }
    //
            //
            //
            //    /**
     //     * файл, который хранит свойства нашего проекта
     //     * создаем объект Properties и загружаем в него данные из файла.
     //     * получаем значения свойств из объекта Properties
     //     * получаем числовое значение
     //     */
            //    public static Properties getProperties() {
        //        Properties config = new Properties();
        //        try (InputStream in = AlertRabbit.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
            //            config.load(in);
            //        } catch (IOException e) {
            //            e.printStackTrace();
            //        }
        //        return config;
        //    }
    //}