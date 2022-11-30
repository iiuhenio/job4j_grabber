package ru.job4j.cache;

import java.lang.ref.SoftReference;
import java.util.Map;
import java.util.HashMap;

/**
 * Создать структуру данных типа кеш (Map). Кеш должен быть абстрактный.
 * То есть необходимо, чтобы можно было задать ключ получения объекта кеша и в случае если его нет в памяти,
 * задать поведение загрузки этого объекта в кеш. Для это выделим класс:
 * @param <K>
 * @param <V>
 */
public abstract class AbstractCache<K, V> {

    protected final Map<K, SoftReference<V>> cache = new HashMap<>();

    public void put(K key, V value) {
        cache.put(key, new SoftReference<>(value));
    }

    public V get(K key) {
        return (V) cache.get(key);


    }

    protected abstract V load(K key);


}
