package ru.job4j.persistence;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.models.Hall;
import ru.job4j.models.Order;

import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;


public class DbStore {
    private static final Logger LOG = LogManager.getLogger(DbStore.class.getName());
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static DbStore singletonInstance =
            new DbStore();
    private Connection connection;
    private final Properties properties = new Properties();

    public DbStore() {
        LOG.info("Constructor DbStore()");
        try (InputStream in = DbStore.class.getClassLoader().getResourceAsStream("db.properties")) {
            this.properties.load(in);
            SOURCE.setDriverClassName(this.properties.getProperty("DriverClassName"));
            SOURCE.setUrl(this.properties.getProperty("path"));
            SOURCE.setUsername(this.properties.getProperty("username"));
            SOURCE.setPassword(this.properties.getProperty("password"));
            SOURCE.setMinIdle(Integer.valueOf(this.properties.getProperty("minIdle")));
            SOURCE.setMaxIdle(Integer.valueOf(this.properties.getProperty("maxIdl")));
            SOURCE.setMaxOpenPreparedStatements(Integer.valueOf(this.properties.getProperty("MaxOpenPreparedStatements")));
            this.connection = SOURCE.getConnection();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        this.query("createHallsTable");
        this.fillHallsTable();
        this.query("createOrdersTable");
    }

    private void query(String query) {
        Savepoint savePoint = null;
        LOG.info(query);
        try (Statement statement = connection.createStatement()) {
            connection.setAutoCommit(false);
            savePoint = connection.setSavepoint();
            statement.execute(this.properties.getProperty(query));
            connection.commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            try {
                connection.rollback(savePoint);
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
        }
    }

    public static DbStore getSingletonInstance() {
        return singletonInstance;
    }

    private void fillHallsTable() {
        Savepoint savePoint = null;
        LOG.info("Try to fill halls");
        try (Statement stFill = connection.createStatement()) {
            connection.setAutoCommit(false);
            savePoint = connection.setSavepoint();
            stFill.execute(this.properties.getProperty("fillHallsTable"));
            connection.commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            try {
                connection.rollback(savePoint);
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
        }
    }

    public void clearAllBlockedPlaces() {
        Savepoint savePoint = null;
        LOG.info("Try to clear all blocked places");
        try (Statement stFill = connection.createStatement()) {
            connection.setAutoCommit(false);
            savePoint = connection.setSavepoint();
            stFill.execute(this.properties.getProperty("clearAllBlockedPlaces"));
            connection.commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            try {
                connection.rollback(savePoint);
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
        }
    }

    public void cleanOrders() {
        Savepoint savePoint = null;
        LOG.info("cleanOrders");
        try (Statement stFill = connection.createStatement()) {
            connection.setAutoCommit(false);
            savePoint = connection.setSavepoint();
            stFill.execute(this.properties.getProperty("cleanOrders"));
            connection.commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            try {
                connection.rollback(savePoint);
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
        }
    }

    public ConcurrentHashMap<Integer, Hall> findAll() {
        Savepoint savePoint = null;
        LOG.info("findAll halls");
        ConcurrentHashMap<Integer, Hall> halls = new ConcurrentHashMap<>();
        try (Statement stFindAll = connection.createStatement()) {
            connection.setAutoCommit(false);
            savePoint = connection.setSavepoint();
            ResultSet result = stFindAll.executeQuery(this.properties.getProperty("findAllHalls"));
            halls = this.findHalls(result);
            result.close();
            connection.commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            try {
                connection.rollback(savePoint);
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
        }
        return halls;
    }
    private ConcurrentHashMap<Integer, Hall> findHalls(ResultSet result) {
        ConcurrentHashMap<Integer, Hall> halls = new ConcurrentHashMap<>();
        try {
            while (result.next()) {
                int number = result.getInt("number");
                int countRanges = result.getInt("count_ranges");
                int countPlaces = result.getInt("count_places");
                Array array = result.getArray("blocked_places");
                Integer[] blockedPlacesArray = (Integer[]) array.getArray();
                ArrayList<Integer> blockedPlaces = new ArrayList<>(Arrays.asList(blockedPlacesArray));
                Hall hall = new Hall(number, countRanges, countPlaces, blockedPlaces);
                halls.put(hall.getNumber(), hall);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return halls;
    }

    public Hall findByNumber(int number) {
        Savepoint savePoint = null;
        LOG.info(String.format("Find hall by number = %s", number));
        Hall hall = new Hall(0, 0, 0, null);
        try (PreparedStatement stForQuery = connection.prepareStatement(this.properties.getProperty("findByNumber"))) {
            connection.setAutoCommit(false);
            savePoint = connection.setSavepoint();
            stForQuery.setInt(1, number);
            ResultSet result = stForQuery.executeQuery();
            if (result.next()) {
                hall.setNumber(result.getInt("number"));
                hall.setCountRanges(result.getInt("count_ranges"));
                hall.setCountPlaces(result.getInt("count_places"));
                Array array = result.getArray("blocked_places");
                Integer[] blockedPlacesArray = (Integer[]) array.getArray();
                ArrayList<Integer> blockedPlaces = new ArrayList<>(Arrays.asList(blockedPlacesArray));
                hall.setBlockedPlaces(blockedPlaces);
            }
            result.close();
            connection.commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            try {
                connection.rollback(savePoint);
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
        }
        return hall;
    }

    public void addBlockedPlaces(int numberOfHall, ArrayList<Integer> newBlockedPlaces) {
        Savepoint savePoint = null;
        LOG.info(String.format("addBlockedPlaces to hall number = %s", numberOfHall));
        try (PreparedStatement statement = connection.prepareStatement(this.properties.getProperty("addBlockedPlaces"))) {
            connection.setAutoCommit(false);
            savePoint = connection.setSavepoint();
            ArrayList<Integer> blockedPlaces = this.getBlockedPlaces(numberOfHall);
            blockedPlaces.addAll(newBlockedPlaces);
            Array array = connection.createArrayOf("INTEGER", blockedPlaces.toArray());
            statement.setArray(1, array);
            statement.setInt(2, numberOfHall);
            statement.execute();
            connection.commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            try {
                connection.rollback(savePoint);
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
        }
    }

    public ArrayList<Integer> getBlockedPlaces(int numberOfHall) {
        Savepoint savePoint = null;
        ArrayList<Integer> blockedPlaces = new ArrayList<>();
        LOG.info(String.format("getBlockedPlaces from hall number = %s", numberOfHall));
        try (PreparedStatement statement = connection.prepareStatement(this.properties.getProperty("getBlockedPlaces"))) {
            connection.setAutoCommit(false);
            savePoint = connection.setSavepoint();
            statement.setInt(1, numberOfHall);
            ResultSet result = statement.executeQuery();
            if (result.next()) {
                Array array = result.getArray("blocked_places");
                Integer[] blockedPlacesArray = (Integer[]) array.getArray();
                blockedPlaces = new ArrayList<>(Arrays.asList(blockedPlacesArray));
            }
            result.close();
            connection.commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            try {
                connection.rollback(savePoint);
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
        }
        return blockedPlaces;
    }

    public void addOrder(Order order) {
        Savepoint savePoint = null;
        LOG.info("addOrder: " + order);
        try (PreparedStatement statement = connection.prepareStatement(this.properties.getProperty("addOrder"))) {
            connection.setAutoCommit(false);
            savePoint = connection.setSavepoint();
            Array array = connection.createArrayOf("INTEGER", order.getPlaces().toArray());
            statement.setString(1, order.getFio());
            statement.setString(2, order.getPhone());
            statement.setInt(3, order.getHallNumber());
            statement.setArray(4, array);
            statement.setInt(5, order.getPrice());
            statement.execute();
            connection.commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            try {
                connection.rollback(savePoint);
            } catch (SQLException e1) {
                LOG.error(e1.getMessage(), e1);
            }
        }
    }
}
