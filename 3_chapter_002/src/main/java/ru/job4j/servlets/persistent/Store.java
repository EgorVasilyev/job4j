package ru.job4j.servlets.persistent;

import ru.job4j.servlets.datamodel.User;

import java.util.concurrent.ConcurrentHashMap;

public interface Store {
    void add(User user);
    void update(int id, User user);
    void delete(int id);
    ConcurrentHashMap<Integer, User> findAll();
    User findById(int id);
}
