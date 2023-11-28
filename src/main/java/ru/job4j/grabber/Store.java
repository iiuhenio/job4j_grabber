package ru.job4j.grabber;

import java.util.List;

/**
 * Наш проект будет хранить данные в базе Postgresql. Связь с базой будет осуществляться через интерфейс.
 * ru.job4j.grabber.Store.
 *
 * Метод save() позволяет сохранить объявление в базе
 * Метод getAll() позволяет вызвать объявление из базы
 * Метод findBy() позволяет извлечь объявление из базы по id
 */
public interface Store {

    void save(Post post);

    List<Post> getAll();

    Post findBy(int id);
}
