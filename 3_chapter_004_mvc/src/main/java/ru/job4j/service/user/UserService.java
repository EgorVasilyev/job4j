package ru.job4j.service.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.dao.user.UserDaoImpl;
import ru.job4j.entity.user.User;

import java.util.List;
@Component
public class UserService {
    private static final Logger LOG = LogManager.getLogger(UserService.class.getName());
    private final UserDaoImpl USER_DAO;
    @Autowired
    public UserService(UserDaoImpl user_dao) {
        USER_DAO = user_dao;
    }

    public int save(User user) {
        int id = 0;
        if (user != null) {
             id = USER_DAO.save(user);
        }
        return id;
    }
    public void update(User user) {
        if (user != null) {
            USER_DAO.update(user);
        }
    }
    public void delete(User user) {
        if (user != null) {
            USER_DAO.delete(user);
        }
    }
    public User isCredential(String login, String password) {
        return this.getUsers().stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
    
    public boolean contains(String login) {
        return this.getUsers().stream().anyMatch(user -> user.getLogin().equals(login));
    }

    public List<User> getUsers() {
        return USER_DAO.getEntities();
    }

    public User getUserById(int id) {
        return USER_DAO.getUserById(id);
    }
}

