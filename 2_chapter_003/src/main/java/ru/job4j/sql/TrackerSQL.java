package ru.job4j.sql;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.models.Item;
import ru.job4j.tracker.ITracker;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class TrackerSQL implements ITracker, AutoCloseable {
    private final Connection connection;
    private static final Logger LOG = LogManager.getLogger(TrackerSQL.class.getName());

    public TrackerSQL(Connection connection) {
        this.connection = connection;
    }

/*    public boolean init() {
        try (InputStream in = TrackerSQL.class.getClassLoader().getResourceAsStream("app.properties")) {
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
    }*/
    @Override
    public Item add(Item item) {
        createItemsTable();
        try (PreparedStatement stForQuery = connection.prepareStatement(
                "INSERT INTO items(id, name, description, create_date) VALUES (?, ?, ?, ?)")) {
            item.setId(String.valueOf(System.currentTimeMillis() + new Random().nextInt()));
            stForQuery.setString(1, item.getId());
            stForQuery.setString(2, item.getName());
            stForQuery.setString(3, item.getDescription());
            stForQuery.setDate(4, Date.valueOf(item.getCreate()));
            stForQuery.execute();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return item;
    }

    @Override
    public void replace(String id, Item item) {
        try (PreparedStatement stForQuery = connection.prepareStatement(
                "update items set name = ?, description = ? where id = ?;")) {
            item.setId(id);
            stForQuery.setString(1, item.getName());
            stForQuery.setString(2, item.getDescription());
            stForQuery.setString(3, item.getId());
            stForQuery.execute();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public boolean delete(String id) {
        boolean deleted = true;
        try (PreparedStatement stForQuery = connection.prepareStatement(
                "delete from items where id = ?;");
             PreparedStatement stForCheck = connection.prepareStatement(
                     "select * from items where id = ?;")) {
            stForQuery.setString(1, id);
            stForQuery.execute();
            stForCheck.setString(1, id);
            ResultSet result = stForCheck.executeQuery();
            if (result.next()) {
                deleted = false;
            }
            result.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return deleted;
    }

    @Override
    public List<Item> findAll() {
        ResultSet result;
        List<Item> itemsList = null;
        try (Statement stForQuery = connection.createStatement()) {
            result = stForQuery.executeQuery("select * from items");
            itemsList = findItems(result);
            result.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return itemsList;
    }

    @Override
    public List<Item> findByName(String name) {
        ResultSet result;
        List<Item> itemsList = null;
        try (PreparedStatement stForQuery = connection.prepareStatement(
                "select * from items where name = ?;")) {
            stForQuery.setString(1, name);
            result = stForQuery.executeQuery();
            itemsList = findItems(result);
            result.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return itemsList;
    }

    @Override
    public Item findById(String id) {
        Item resultItem = new Item(null, null);
        try (PreparedStatement stForQuery = connection.prepareStatement(
                "select * from items where id = ?")) {
            stForQuery.setString(1, id);
            ResultSet result = stForQuery.executeQuery();
            if (result.next()) {
                resultItem.setId(id);
                resultItem.setName(result.getString("name"));
                resultItem.setDescription(result.getString("description"));
                resultItem.setCreate(result.getString("create_date"));
            }
            result.close();
            if (resultItem.getId() == null
                    && resultItem.getName() == null
                    && resultItem.getDescription() == null) {
                resultItem = null;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return resultItem;
    }

    //управление закрытием соединения передано классу ConnectionRollback
    @Override
    public void close() {
    }

    private void createItemsTable() {
        try (Statement stForCreateItems = connection.createStatement()) {
            stForCreateItems.execute("create table if not exists items(\n"
                    + "\tid varchar,\n"
                    + "\tname varchar(200),\n"
                    + "\tdescription varchar(200),\n"
                    + "\tcreate_date date\n"
                    + ");");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private List<Item> findItems(ResultSet result) {
        List<Item> itemsList = new ArrayList<>();
        try {
            while (result.next()) {
                Item resultItem = new Item(result.getString("name"), result.getString("description"));
                resultItem.setId(result.getString("id"));
                resultItem.setCreate(result.getString("create_date"));
                itemsList.add(resultItem);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return itemsList;
    }

    public void deleteItemsTable() {
        try (Statement st = connection.createStatement()) {
            st.execute("drop table items;");
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }
}
