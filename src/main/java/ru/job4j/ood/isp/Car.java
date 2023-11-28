package ru.job4j.ood.isp;

/**
 * В интерфейсе содержится три метода. Но не все классы используют их
 * Правильнее было бы в каждый интефейс поместить по одному методу и использовать те, которые нужны.
 */
public interface Car {

    void drive();

    void fill();

    void load();

}

class Cars implements Car {

    @Override
    public void drive() {
        System.out.println("Drive");
    }

    @Override
    public void fill() {
        System.out.println("Fill");
    }

    @Override
    public void load() {
        throw new UnsupportedOperationException("This is not a truck!");
    }
}

class Trucks implements Car {

    @Override
    public void drive() {
        System.out.println("Drive");
    }

    @Override
    public void fill() {
        System.out.println("Fill");
    }

    @Override
    public void load() {
        System.out.println("Load");
    }
}

class ToyCars implements Car {

    @Override
    public void drive() {
        System.out.println("Drive");
    }

    @Override
    public void fill() {
        throw new UnsupportedOperationException("This is toy car!");
    }

    @Override
    public void load() {
        throw new UnsupportedOperationException("This is not a truck!");
    }
}