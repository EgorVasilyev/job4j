package ru.job4j.parsersqlru;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Properties;
/**
 * Class VacancySQL. Генерация данных в БД Postgresql
 */
public class VacancySQL implements AutoCloseable {
    private Connection connection;
    private static final Logger LOG = LogManager.getLogger(VacancySQL.class.getName());

    public boolean init() {
        try (InputStream in = VacancySQL.class.getClassLoader().getResourceAsStream("appJobMarketSQL.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            this.connection = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
        return this.connection != null;
    }

    @Override
    public void close() throws Exception {
        if (this.connection != null) {
            this.connection = null;
        }
    }

    public void add(Vacancy vacancy) {
        createVacancyTable();
        try (PreparedStatement stForQuery = connection.prepareStatement(
                "INSERT INTO vacancy(name, text, link) VALUES (?, ?, ?)")) {
            stForQuery.setString(1, vacancy.getName());
            stForQuery.setString(2, vacancy.getText());
            stForQuery.setString(3, vacancy.getLink());
            stForQuery.execute();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private void createVacancyTable() {
        try (Statement stForCreateItems = connection.createStatement()) {
            stForCreateItems.execute("create table if not exists vacancy(\n"
                    + "\tid serial primary key,\n"
                    + "\tname varchar(200),\n"
                    + "\ttext varchar(5000),\n"
                    + "\tlink varchar(200)"
                    + ");");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
