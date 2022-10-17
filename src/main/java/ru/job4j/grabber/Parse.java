package ru.job4j.grabber;

import java.util.List;

/**
 * Создадим интерфейс описывающий парсинг сайта.
 * Метод list загружает список всех постов. В нем нужно спарсить 5 страниц.
 */
public interface Parse {
    List<Post> list(String link);
}
