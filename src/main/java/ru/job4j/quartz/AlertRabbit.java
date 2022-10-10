package ru.job4j.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.io.*;
import java.util.Properties;

import static org.quartz.JobBuilder.*;
import static org.quartz.TriggerBuilder.*;
import static org.quartz.SimpleScheduleBuilder.*;

/**
 * 1. Конфигурирование
 * Начало работы происходит с создания класса управляющего всеми работами.
 * В объект Scheduler мы будем добавлять задачи, которые хотим выполнять периодически.
 * 2. Создание задачи.
 * 3. Создание расписания
 * Конструкция ниже настраивает периодичность запуска.
 * В нашем случае, мы будем запускать задачу через 10 секунд и делать это бесконечно.
 * 4. Задача выполняется через триггер
 * Здесь можно указать, когда начать запуск.
 * Мы хотим сделать это сразу
 * 5. Загрузка задачи и триггера в планировщик
 */
public class AlertRabbit {
    public static void main(String[] args) {
        try {

            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();
            JobDetail job = newJob(Rabbit.class).build();
            SimpleScheduleBuilder times = simpleSchedule()
                    .withIntervalInSeconds(Integer.parseInt(AlertRabbit.genProperties().getProperty("rabbit.interval")))
                    .repeatForever();
            Trigger trigger = newTrigger()
                    .startNow()
                    .withSchedule(times)
                    .build();
            scheduler.scheduleJob(job, trigger);
        } catch (SchedulerException se) {
            se.printStackTrace();
        }
    }

    /**
     * (2)
     * quartz каждый раз создает объект с типом org.quartz.Job.
     * Вам нужно создать класс реализующий этот интерфейс.
     * Внутри этого класса нужно описать требуемые действия.
     * В нашем случае - это вывод на консоль текста.
     */
    public static class Rabbit implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            System.out.println("Rabbit runs here ...");
        }
    }

    /**
     * файл, который хранит свойства нашего проекта
     * создаем объект Properties и загружаем в него данные из файла.
     * получаем значения свойств из объекта Properties
     * получаем числовое значение
     */

    public static Properties genProperties() {
        Properties config = new Properties();
        try (InputStream in = AlertRabbit.class.getClassLoader().getResourceAsStream("rabbit.properties")) {
            config.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return config;
    }
}