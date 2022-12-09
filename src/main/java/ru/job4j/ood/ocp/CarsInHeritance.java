package ru.job4j.ood.ocp;

import java.util.List;

public class CarsInHeritance {

    private static class Car {
        public String sound() {
            return "beep-beep";
        }
    }

    public static void main(String[] args) {
        List<Car> cars = List.of(new Car(), new Car());
        cars.forEach(Car::sound);
    }
}