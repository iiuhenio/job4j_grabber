package ru.job4j.ood.ocp;

/**
 * Данный класс описывает поведение машин. Методы gas() и stop() используются во всех классах-наследниках
 * А метод fill() не используется с ирушечными машинами. По этому чтобы не переопределять его каждый раз,
 * его нужно вынести в отдельный интерфейс
 */
public class Auto {

    public void gas() {
        System.out.println("Едем вперед");
    }

    public void stop() {
        System.out.println("Тормозим!");
    }

    public void fill() {
        System.out.println("Заправить бензин!");
    }

    private static class Sedan extends Auto {
    }

    private static class Truck extends Auto {
    }

    private static class F1Car extends Auto {
    }

    private static class ChildrenBuggies extends Auto {
        @Override
        public void fill() {
            System.out.println("Игрушечную машину заправлять нельзя");
        }
    }
}







