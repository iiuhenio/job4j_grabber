package ru.job4j.gc.prof;

/**
 * Класс, который генерирует массив:
 */
public interface Data {
    void insert(int elements);

    int[] getClone();
}