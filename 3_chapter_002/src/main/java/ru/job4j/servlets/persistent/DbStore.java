package ru.job4j.servlets.persistent;

import org.apache.commons.dbcp2.BasicDataSource;
import ru.job4j.servlets.datamodel.User;

import java.sql.Connection;
import java.sql.Statement;
import java.util.concurrent.ConcurrentHashMap;

public class DbStore implements Store {
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static final DbStore INSTANCE = new DbStore();

    public DbStore() {
        SOURCE.setUrl("...");
        SOURCE.setUsername("...");
        SOURCE.setPassword("...");
        SOURCE.setMinIdle(5);
        SOURCE.setMaxIdle(10);
        SOURCE.setMaxOpenPreparedStatements(100);
    }

    public static DbStore getInstance() {
        return INSTANCE;
    }
    @Override
    public void add(User user) {
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.prepareStatement("...")
        ) {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, User user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public ConcurrentHashMap<Integer, User> findAll() {
        return null;
    }

    @Override
    public User findById(int id) {
        return null;
    }
}
