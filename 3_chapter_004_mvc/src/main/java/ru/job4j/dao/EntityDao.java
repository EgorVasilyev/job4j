package ru.job4j.dao;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface EntityDao<T> {
    List<T> getEntities();
    @Transactional
    int save(T t);
    @Transactional
    void update(T t);
    @Transactional
    void delete(T t);
}
