package ru.job4j.ood.srp;

/**
 * В данном классе, используя паттерн Singleton, добавляем зказы в массив, а также выводим их на экран
 * и добавляем в файл.
 * Правильней было бы сохранение и вывод перенести в другой класс
 */
public class Singleton {
    private static Singleton instance;
    private String[] orders = new String[1000];
    private int count = 0;
    private Singleton() {
    };

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }

    public void add(String order) {
        orders[count++] = order;
    }

    public void save() {
    }

    public static void print() {
    }
}
