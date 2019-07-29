package ru.job4j.storage;

import ru.job4j.storage.entity.User;
import ru.job4j.storage.store.Storage;

import java.util.List;

/**
 * @author Yury Matskevich
 */
public class ImportUser {
    private Storage storage;

    public ImportUser(Storage store) {
        this.storage = store;
    }

    public void add(User user) {
        storage.add(user);
    }

    public List<User> getUsers() {
        return storage.getUsers();
    }
}
