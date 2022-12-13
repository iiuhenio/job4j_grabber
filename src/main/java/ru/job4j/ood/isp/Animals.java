package ru.job4j.ood.isp;

/**
 * Не все животные могут совершать все действия
 * Нужо разделить их на группы и сделать для каждой отдельный интерфейс
 */
public interface Animals {
    void voice();
    void fly();
    void changeColor();
    void scratch();
    void run();
    void seat();
}
