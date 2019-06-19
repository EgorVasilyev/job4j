package ru.job4j.servlets.logic;

import ru.job4j.servlets.datamodel.User;
import ru.job4j.servlets.persistent.DbStore;
import ru.job4j.servlets.persistent.Store;

import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Logic Layout
 *
 * Далее создайте класс ValidateService - это слой Logic. В нем нужно добавить методы
 * add, update, delete, findAll, findById
 *
 * Каждый метод должен производить валидацию данных. Например, при обновлении полей нужно проверить. что такой пользователь существует.
 *
 *
 * Класс ValidateService сделать синглетоном. Использовать Eager initiazitation.
 */

public class ValidateService implements Validate {
    private final Store persistent = DbStore.getSingletonInstance();
    // Static member holds only one instance of the
    // ValidateService class
    private static Validate singletonInstance =
            new ValidateService();

    // ValidateService prevents any other class from instantiating
    private ValidateService() {
    }

    // Providing Global point of access
    public static Validate getSingletonInstance() {
        return singletonInstance;
    }

    @Override
    public User add(User user) {
        if (user.getName() != null && user.getLogin() != null && user.getEmail() != null && user.getPassword() != null) {
            this.persistent.add(user);
        }
        return user;
    }
    @Override
    public void update(int id, User user) {
        if (user.getName() != null && user.getLogin() != null && user.getEmail() != null && user.getPassword() != null) {
            this.persistent.update(id, user);
        }
    }
    @Override
    public boolean delete(int id) {
        Optional<User> userOptional = Optional.ofNullable(this.findById(id));
        boolean result = userOptional.isPresent();
        if (result) {
            this.persistent.delete(id);
        }
        return result;
    }
    @Override
    public ConcurrentHashMap<Integer, User> findAll() {
        return this.persistent.findAll();
    }
    @Override
    public User findById(int id) {
        return this.persistent.findById(id);
    }
    @Override
    public int isCredential(final String login, final String password) {
        int id = 0;
        User foundUser = this.persistent.findAll().values().stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findFirst().orElse(null);
        if (foundUser != null) {
            id = foundUser.getId();
        }
        return id;
    }
}