package ru.job4j.cache;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Создать программу, имитирующую поведение данного кеша.
 * Программа должна считывать текстовые файлы и выдавать текст при запросе имени файла.
 * Если в кеше файла нет, кеш должен загрузить себе данные. По умолчанию в кеше нет ни одного файла.
 * Текстовые файлы должны лежать в одной директории. Пример. Names.txt, Address.txt - файлы в системе.
 * При запросе по ключу Names.txt - кеш должен вернуть содержимое файла Names.txt.
 */

public class DirFileCache extends AbstractCache<String, String> {

    private final String cachingDir;

    public DirFileCache(String cachingDir) {
        this.cachingDir = cachingDir;
    }

    @Override
    protected String load(String key) {
        String rsl = null;
        try {
            rsl = Files.readString(Path.of(cachingDir, key));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return rsl;
    }

}