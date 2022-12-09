package ru.job4j.ood.ocp;

import java.util.List;

/**
 * В данном примере мы создаем класс, который считает площадь городов
 * Затем создаем класс, который считает площадь треугольника, наследуя его от первого.
 * Но в данном случае наследование невозможно, т.к. это разные объекты с разными характеристиками
 */
public class AreaUsage {

    public static class City {
        public void square() {
        }
    }

    public static class Triangular extends City {
        @Override
        public void square() {
        }
    }

    public static void main(String[] args) {
        List<City> dogs = List.of(new City());
        dogs.forEach(City::square);
    }
}
