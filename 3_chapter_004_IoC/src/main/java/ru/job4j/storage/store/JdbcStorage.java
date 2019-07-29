package ru.job4j.storage.store;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.storage.entity.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JdbcStorage implements Storage {
    private static final Logger LOG = LogManager.getLogger(JdbcStorage.class.getName());
    private String url;
    private String userName;
    private String password;

    public JdbcStorage() {
    }

    public JdbcStorage(String url, String userName, String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    private void init() {
        String createTableQuery = "create table if not exists users ("
                + "id serial primary key,"
                + "name_u varchar not null,"
                + "age_u integer not null"
                + ");";
        try (Connection connection = this.getConnection();
             Statement st = connection.createStatement()) {
            st.execute(createTableQuery);
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, userName, password);
    }
    @Override
    public void add(User user) {
        this.init();
        String addQuery = "insert into users (name_u, age_u) values (?, ?);";
        try (Connection connection = this.getConnection();
             PreparedStatement ps = connection.prepareStatement(addQuery)) {
            ps.setString(1, user.getName());
            ps.setInt(2, user.getAge());
            ps.execute();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }
    @Override
    public List<User> getUsers() {
        String getQuery = "select * from users";
        List<User> users = new ArrayList<>();
        try (Connection connection = this.getConnection();
             Statement st = connection.createStatement();
             ResultSet resultSet = st.executeQuery(getQuery)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setName(resultSet.getString(2));
                user.setAge(resultSet.getInt(3));
                users.add(user);
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return users;
    }
}
