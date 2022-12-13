package ru.job4j.ood.lsp;

/**
 * 1. 1. Предусловия (Preconditions) не могут быть усилены в подклассе
 *
 * От AutoTransport мы ожидаем, что машина сдвинется, но нет.
 * Автобус не сдвигается, т.к. в нем усилено предусловие. Ожидаем мы одно поведение, а получаем другое.
 * Чтобы сдвинуть автобус нам придется дописать доп.условие, чтобы проверить является ли транспорт автобусом.
 * Далее уже скормить ему больше топлива.
 */
class AutoTransport {

    protected float fuel;

    public AutoTransport(float fuel) {
        this.fuel = fuel;
    }

    public void move(float km) {
        if (km < 0) {
            throw new IllegalArgumentException("Invalid distance!");
        }
        if (fuel < 0) { /* <= предусловие */
            throw new IllegalArgumentException("Need more fuel!");
        }
        /* other logic */
    }

}

class Bus extends AutoTransport {

    public Bus(float fuel) {
        super(fuel);
    }

    public void move(float km) {
        if (km < 0) {
            throw new IllegalArgumentException("Invalid distance!");
        }
        if (fuel < 5) { /* условие усилено */
            throw new IllegalArgumentException("Need more fuel!");
        }
        /* other logic */
    }

}

class FirstRule {
    public static void main(String[] args) {
        AutoTransport bus = new Bus(3); /* в объект бус типа АвтоТранспорт записываем объект Бус */
        bus.move(2);
    }
}