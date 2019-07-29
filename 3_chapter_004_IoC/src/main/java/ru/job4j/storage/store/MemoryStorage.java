package ru.job4j.storage.store;

import ru.job4j.storage.entity.User;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class MemoryStorage implements Storage {
    private List<User> users = new CopyOnWriteArrayList<>();
    @Override
    public void add(User user) {
        users.add(user);
    }
    @Override
    public List<User> getUsers() {
        return this.users;
    }
}
