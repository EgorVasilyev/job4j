package ru.job4j.sql;

import org.junit.Test;
import ru.job4j.models.Item;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class TrackerSQLTest {
    public Connection init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }
    @Test
    public void whenAddOneAndFindAllThenOneItem() throws SQLException {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            trackerSQL.add(new Item("firstName", "firstDesc"));
            String expectedName = "firstName";
            String expectedDesc = "firstDesc";
            assertThat(trackerSQL.findAll().size(), is(1));
            assertThat(trackerSQL.findAll().get(0).getName(), is(expectedName));
            assertThat(trackerSQL.findAll().get(0).getDescription(), is(expectedDesc));
        }
    }
    @Test
    public void whenAddOneAndReplaceThenNewName() throws SQLException {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item firstItem = new Item("firstName", "firstDesc");
            trackerSQL.add(firstItem);
            trackerSQL.replace(firstItem.getId(), new Item("replacedName", "replacedDesc"));
            String expectedName = "replacedName";
            String expectedDesc = "replacedDesc";
            assertThat(trackerSQL.findAll().size(), is(1));
            assertThat(trackerSQL.findAll().get(0).getName(), is(expectedName));
            assertThat(trackerSQL.findAll().get(0).getDescription(), is(expectedDesc));
        }
    }
    @Test
    public void whenAddOneAndDeleteThenThereIsNotAnItem() throws SQLException {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item firstItem = new Item("firstName", "firstDesc");
            trackerSQL.add(firstItem);
            trackerSQL.delete(firstItem.getId());
            assertThat(trackerSQL.findAll().size(), is(0));
        }
    }
    @Test
    public void whenAddTwoAndFindAllThenTwoItem() throws SQLException {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            trackerSQL.add(new Item("firstName", "firstDesc"));
            trackerSQL.add(new Item("secondName", "secondDesc"));
            String expectedName = "firstName";
            String expectedDesc = "secondDesc";
            assertThat(trackerSQL.findAll().size(), is(2));
            assertThat(trackerSQL.findAll().get(0).getName(), is(expectedName));
            assertThat(trackerSQL.findAll().get(1).getDescription(), is(expectedDesc));
        }
    }
    @Test
    public void whenAddOneAndFindByIdThenOneItem() throws SQLException {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item firstItem = new Item("firstName", "firstDesc");
            trackerSQL.add(firstItem);
            Item resultItem = trackerSQL.findById(firstItem.getId());
            String expectedName = "firstName";
            String expectedDesc = "firstDesc";
            assertThat(resultItem.getName(), is(expectedName));
            assertThat(resultItem.getDescription(), is(expectedDesc));
        }
    }
    @Test
    public void whenAddTwoAndFindByNameThenTwoItem() throws SQLException {
        try (TrackerSQL trackerSQL = new TrackerSQL(ConnectionRollback.create(this.init()))) {
            Item firstItem = new Item("Name", "firstDesc");
            Item secondItem = new Item("Name", "secondDesc");
            trackerSQL.add(firstItem);
            trackerSQL.add(secondItem);
            String expectedFirstDesc = "firstDesc";
            String expectedSecondDesc = "secondDesc";
            assertThat(trackerSQL.findByName("Name").get(0).getDescription(), is(expectedFirstDesc));
            assertThat(trackerSQL.findByName("Name").get(1).getDescription(), is(expectedSecondDesc));
        }
    }
}
