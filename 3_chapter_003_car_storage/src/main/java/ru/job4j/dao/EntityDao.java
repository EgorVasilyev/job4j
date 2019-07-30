package ru.job4j.dao;

import java.util.List;

public interface EntityDao<T> {
    List<T> getEntities();
    void save(T t);
    void update(T t);
    void delete(T t);
}
