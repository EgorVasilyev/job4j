package ru.job4j.sqlxmlxstl;

import java.io.InputStream;
import java.util.Properties;

/**
 * Class Config - Настройки для подключения к базе данных.
 */
public class Config {
    private final Properties values = new Properties();

    public void init() {
        try (InputStream in = Config.class.getClassLoader().getResourceAsStream("appSQLite.properties")) {
            values.load(in);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public String get(String key) {
        return this.values.getProperty(key);
    }

}