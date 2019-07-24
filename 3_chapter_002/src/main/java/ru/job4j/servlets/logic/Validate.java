package ru.job4j.servlets.logic;

import ru.job4j.servlets.datamodel.User;

import java.util.Map;

public interface Validate {
    User add(User user);
    void update(int id, User user);
    boolean delete(int id);
    Map<Integer, User> findAll();
    User findById(int id);
    int isCredential(String login, String password);
}
