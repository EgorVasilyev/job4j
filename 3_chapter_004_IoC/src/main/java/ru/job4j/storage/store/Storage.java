package ru.job4j.storage.store;

import ru.job4j.storage.entity.User;

import java.util.List;

public interface Storage {
    void add(User user);
    List<User> getUsers();
}
