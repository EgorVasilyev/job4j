package ru.job4j.servlets.logic;

import ru.job4j.servlets.datamodel.User;

import java.util.concurrent.ConcurrentHashMap;

public interface Validate {
    User add(User user);
    void update(int id, User user);
    boolean delete(int id);
    ConcurrentHashMap<Integer, User> findAll();
    User findById(int id);
    int isCredential(String login, String password);
}
