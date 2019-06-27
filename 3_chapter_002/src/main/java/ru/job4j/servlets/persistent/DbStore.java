package ru.job4j.servlets.persistent;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.servlets.datamodel.User;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;


public class DbStore implements Store {
    private static final Logger LOG = LogManager.getLogger(DbStore.class.getName());
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private static Store singletonInstance =
            new DbStore();
    private final AtomicInteger id = new AtomicInteger(0);
    private Properties properties;

    public DbStore() {
        LOG.info("Constructor DbStore()");
        try (InputStream in = DbStore.class.getClassLoader().getResourceAsStream("db.properties")) {
            this.properties = new Properties();
            this.properties.load(in);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        SOURCE.setDriverClassName(this.properties.getProperty("DriverClassName"));
        SOURCE.setUrl(this.properties.getProperty("path"));
        SOURCE.setUsername(this.properties.getProperty("username"));
        SOURCE.setPassword(this.properties.getProperty("password"));
        SOURCE.setMinIdle(Integer.valueOf(this.properties.getProperty("minIdle")));
        SOURCE.setMaxIdle(Integer.valueOf(this.properties.getProperty("maxIdl")));
        SOURCE.setMaxOpenPreparedStatements(Integer.valueOf(this.properties.getProperty("MaxOpenPreparedStatements")));
        this.doQuery("Try to create users table", "createUsersTable");
        this.id.set(this.getActualId());
    }

    private void doQuery(String logInfo, String queryName) {
        LOG.info(logInfo);
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.createStatement()) {
            st.execute(this.properties.getProperty(queryName));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    private int getActualId() {
        synchronized (this.id) {
            LOG.info("Get actual ID from DB");
            try (Connection connection = SOURCE.getConnection();
                 PreparedStatement stForQuery = connection.prepareStatement(this.properties.getProperty("queryGetActualId"));
                 ResultSet result = stForQuery.executeQuery()) {
                if (result.next()) {
                    return result.getInt("max");
                }
            } catch (Exception e) {
                LOG.error(e.getMessage(), e);
            }
        }
        return 0;
    }

    public static Store getSingletonInstance() {
        return singletonInstance;
    }

    @Override
    public void add(User user) {
        LOG.info(String.format("Try to add new %s", user));
        this.id.set(this.getActualId());
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement stAdd = connection.prepareStatement(this.properties.getProperty("queryAdd"))) {
            stAdd.setString(1, user.getName());
            stAdd.setString(2, user.getLogin());
            stAdd.setString(3, user.getEmail());
            stAdd.setString(4, user.getCreateDate().toString());
            stAdd.setString(5, user.getPassword());
            stAdd.setInt(6, id.incrementAndGet());
            stAdd.setString(7, user.getRole());
            stAdd.setInt(8, user.getCountryId());
            stAdd.setInt(9, user.getCityId());
            stAdd.execute();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void update(int id, User user) {
        LOG.info(String.format("Try to update user with id = %s", id));
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement stUpdate = connection.prepareStatement(this.properties.getProperty("queryUpdate"))) {
            stUpdate.setString(1, user.getName());
            stUpdate.setString(2, user.getLogin());
            stUpdate.setString(3, user.getEmail());
            stUpdate.setString(4, user.getPassword());
            stUpdate.setString(5, user.getRole());
            stUpdate.setInt(6, user.getCountryId());
            stUpdate.setInt(7, user.getCityId());
            stUpdate.setInt(8, id);
            stUpdate.execute();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        LOG.info(String.format("Try to delete user with id = %s", id));
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement stDel = connection.prepareStatement(this.properties.getProperty("queryDelete"))) {
            stDel.setInt(1, id);
            stDel.execute();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        this.id.set(this.getActualId());
    }

    @Override
    public ConcurrentHashMap<Integer, User> findAll() {
        LOG.info("Show users");
        ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
        try (Connection connection = SOURCE.getConnection();
             Statement stFindAll = connection.createStatement()) {
            ResultSet result = stFindAll.executeQuery(this.properties.getProperty("queryFindAll"));
            users = this.findUsers(result);
            result.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return users;
    }
    private ConcurrentHashMap<Integer, User> findUsers(ResultSet result) {
        ConcurrentHashMap<Integer, User> users = new ConcurrentHashMap<>();
        try {
            while (result.next()) {
                String name = result.getString("name_u");
                String login = result.getString("login_u");
                String email = result.getString("email_u");
                String password = result.getString("password_u");
                String role = result.getString("role_u");
                int id = result.getInt("id_u");
                Date date =  new SimpleDateFormat(
                        "E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
                        .parse(result.getString("create_date")
                        );
                int country = result.getInt("country_id");
                int city = result.getInt("city_id");
                User user = new User(name, login, email, password, role, country, city);
                user.setId(id);
                user.setCreateDate(date);
                users.put(user.getId(), user);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return users;
    }

    @Override
    public User findById(int id) {
        LOG.info(String.format("Find user by id = %s", id));
        User user = new User(null, null, null, null, null, 1, 1);
        try (Connection connection = SOURCE.getConnection();
             PreparedStatement stForQuery = connection.prepareStatement(this.properties.getProperty("queryFindById"))) {
            stForQuery.setInt(1, id);
            ResultSet result = stForQuery.executeQuery();
            if (result.next()) {
                user.setId(id);
                user.setName(result.getString("name_u"));
                user.setLogin(result.getString("login_u"));
                user.setEmail(result.getString("email_u"));
                user.setPassword(result.getString("password_u"));
                user.setRole(result.getString("role_u"));
                user.setCreateDate(
                        new SimpleDateFormat(
                                "E MMM dd HH:mm:ss z yyyy", Locale.ENGLISH)
                                .parse(result.getString("create_date")
                                )
                );
                user.setCountryId(result.getInt("country_id"));
                user.setCityId(result.getInt("city_id"));
            }
            result.close();
            if (user.getName() == null
                    && user.getLogin() == null
                    && user.getEmail() == null
                    && user.getPassword() == null
                    && user.getRole() == null) {
                user = null;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }
}
