package ru.job4j.ood.srp;

import java.util.Arrays;

/**
 * Класс отвечает за нахождение, вывод, и сохранение максимального числа из массива
 * Правильнее будет эти методы поместить в отдельные классы.
 */
public class ArrayMax {

    public static void main(int[] array) {
        Arrays.sort(array);
        int max = array[array.length - 1];
        System.out.println(max);
    }

    public void save() {
    }
}
