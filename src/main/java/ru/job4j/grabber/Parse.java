package ru.job4j.grabber;

import java.util.List;

/**
 * Создадим интерфейс описывающий парсинг сайта.
 * Метод list загружает список всех постов. В нем нужно спарсить 5 страниц.
 *
 * Этот компонент позволяет собрать короткое описание всех объявлений,
 * а так же загрузить детали по каждому объявлению.
 * list(link) - этот метод загружает список объявлений по ссылке типа -
 * career.habr.com/vacancies/java_developer?page=1
 * Описание компонента через интерфейс позволяет расширить наш проект.
 * Например, осуществить сбор данных с других площадок: SqlRuParse, SuperJobParse.
 */
public interface Parse {
    List<Post> list(String link);
}
