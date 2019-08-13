package ru.job4j.dao;

import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Transactional
public interface EntityDao<T> {
    List<T> getEntities();

    int save(T t);
    void update(T t);
    void delete(T t);
}
