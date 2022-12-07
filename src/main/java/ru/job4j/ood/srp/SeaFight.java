package ru.job4j.ood.srp;

import java.util.Scanner;

/**
 * Игра "Морской бой". Один метод выводит игровое поле, другой создает координаты кораблей.
 * Правильней было бы сделать два класса, в каждом из которых поместить данные методы по одному.
 */
public class SeaFight {

    public static void set(int i, int j) {
        int[][] array = new int[i][j];
        for (i = 0; i < array.length; i++) {
            for (j = 0; j < array[i].length; j++) {
                array[i][j] = 0;
                System.out.print(" " + array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void location(int[][] array) {
        Scanner console = new Scanner(System.in);
        System.out.println("Задайте координаты корабля");
        int a = console.nextInt();
        System.out.println("Задайте координаты корабля");
        int b = console.nextInt();
        array[a][b] = 1;
    }
}
