package ru.job4j.ood.dip;

/**
 * Данные два класса взимодействуют друг с другом на прямую, а должны через абстракцию
 */
public class Husband {

    Wife wife = new Wife();

    void giveMoney() {
        wife.shop();
    }
}

class Wife {
    void shop() {
        System.out.println("Shopping");
    }
}
