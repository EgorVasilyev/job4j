package ru.job4j.tracker;

import ru.job4j.models.Item;

import java.util.List;

public interface ITracker {
    Item add(Item item);
    void replace(String id, Item item);
    boolean delete(String id);
    List<Item> findAll();
    List<Item> findByName(String key);
    Item findById(String id);
}
