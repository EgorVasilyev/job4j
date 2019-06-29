package ru.job4j.htmlcssjs.persistent;

import ru.job4j.htmlcssjs.datamodel.JsonUser;

import java.util.concurrent.ConcurrentHashMap;

public interface Store {
    void add(JsonUser user);
    void update(int id, JsonUser user);
    void delete(int id);
    ConcurrentHashMap<Integer, JsonUser> findAll();
    JsonUser findById(int id);
}
