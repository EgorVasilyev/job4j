package ru.job4j.servlets.persistent;

import ru.job4j.servlets.datamodel.User;

import java.util.Map;

public interface Store {
    void add(User user);
    void update(int id, User user);
    void delete(int id);
    Map<Integer, User> findAll();
    User findById(int id);
}
