package ru.job4j.dao;

import ru.job4j.entity.Item;
import java.util.List;

public interface ItemDAO {
    List<Item> getItems();
    List<Item> getDoneItems();
    void save(Item item);
    void update(Item item);
}
