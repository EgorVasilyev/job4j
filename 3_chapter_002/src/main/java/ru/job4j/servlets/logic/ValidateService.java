package ru.job4j.servlets.logic;

import ru.job4j.servlets.datamodel.User;
import ru.job4j.servlets.persistent.MemoryStore;
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

public class ValidateService {
    private final Store persistent = MemoryStore.getSingletonInstance();
    // Static member holds only one instance of the
    // ValidateService class
    private static ValidateService singletonInstance =
            new ValidateService();

    // ValidateService prevents any other class from instantiating
    private ValidateService() {
    }

    // Providing Global point of access
    public static ValidateService getSingletonInstance() {
        return singletonInstance;
    }

    public boolean add(User user) {
        boolean result = false;
        if (user != null && !this.persistent.findAll().contains(user)) {
            result = true;
            this.persistent.add(user);
        }
        return result;
    }
    public void update(int id, String newName, String newLogin, String newEmail) {
        if (newName != null && newLogin != null && newEmail != null) {
            this.persistent.update(id, new User(newName, newLogin, newEmail));
        }
    }
    public boolean delete(int id) {
        Optional<User> userOptional = Optional.ofNullable(this.findById(id));
        boolean result = userOptional.isPresent();
        if (result) {
            this.persistent.delete(id);
        }
        return result;
    }
    public ConcurrentHashMap<Integer, User> findAll() {
        return this.persistent.findAll();
    }
    public User findById(int id) {
        return this.persistent.findById(id);
    }
}
