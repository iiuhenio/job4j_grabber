package ru.job4j.grabber;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

public class PsqlStore implements Store, AutoCloseable {

    private Connection cnn;

    public PsqlStore(Properties cfg) {
        try {
            Class.forName(cfg.getProperty("jdbc.driver"));
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }





    @Override
    public void close() throws Exception {

    }

    @Override
    public void save(Post post) {

    }

    @Override
    public List<Post> getAll() {
        return null;
    }

    @Override
    public Post findBy(int id) {
        return null;
    }
}
