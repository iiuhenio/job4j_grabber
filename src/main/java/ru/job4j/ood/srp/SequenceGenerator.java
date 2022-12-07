package ru.job4j.ood.srp;

import java.util.List;

/**
 * Допустим нам нужно написать класс, который генерирует последовательность случайных чисел и выводит ее.
 * Сперва мы выделяем абстракцию. В нашем случае это будет интерфейс SequenceGenerator
 */
public interface SequenceGenerator<T> {
    List<T> generate(int size);
}