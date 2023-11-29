package ru.job4j.grabber;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;

/**
 * В этом проекты мы будем использовать quartz для запуска парсера.
 * Но напрямую мы не будем его использовать.
 * Абстрагируемся через интерфейс.
 */
public interface Grab {
    void init() throws SchedulerException;
}
