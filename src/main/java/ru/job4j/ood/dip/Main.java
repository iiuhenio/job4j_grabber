package ru.job4j.ood.dip;

import java.util.function.Consumer;

/**
 * Здесь проблема в том, что например, метод main() использует класс DataAcess,
 * тот в свою очередь обращается к классу Client итд
 * В соответствии с принципом DIP должно быть разделение на верхние классы и нижние классы
 * и они не должны знать друг о друге.
 * Нужно было бы сделать промежуточные интерфейс, который бы взаимодействовал между уровнями
 */
public class Main {
    public static void main(String[] args) {
        Consumer<DataAccess> execute = DataAccess::execute;
    }
}

class DataAccess {
    void execute() {
        System.out.println("execute");
    }
}

class Client {
    DataAccess da = new DataAccess();

    void doJob() {
        da.execute();
    }
}