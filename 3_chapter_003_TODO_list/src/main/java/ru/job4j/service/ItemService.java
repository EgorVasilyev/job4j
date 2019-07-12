package ru.job4j.service;

import ru.job4j.dao.ItemDAO;
import ru.job4j.dao.ItemDAOImpl;
import ru.job4j.entity.Item;

import java.util.List;

public class ItemService {
    private static final ItemService INSTANCE = new ItemService();
    private final ItemDAO itemDAO = ItemDAOImpl.getInstance();

    private ItemService() {
    }

    public static ItemService getInstance() {
        return INSTANCE;
    }

    public List<Item> getItems() {
        return itemDAO.getItems();
    }

    public List<Item> getNotDoneItems() {
        return itemDAO.getNotDoneItems();
    }

    public Item getById(int id) {
        return itemDAO.getById(id);
    }

    public void save(Item item) {
        if (item != null) {
            itemDAO.save(item);
        }
    }

    public void update(Item item) {
        if (item != null) {
            itemDAO.update(item);
        }
    }

    public void delete(Item item) {
        if (item != null) {
            itemDAO.delete(item);
        }
    }
}
