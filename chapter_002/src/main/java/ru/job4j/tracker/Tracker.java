package ru.job4j.tracker;
import ru.job4j.models.*;

import java.util.*;

/**
 * @version 2
 * @since 10/11/2018
 */
public class Tracker {
    private static final Random RN = new Random();
    /**
     * Списочный массив для хранение заявок.
     */
    private final List<Item> items = new ArrayList<Item>();

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items.add(item);
        return item;
    }

    /**
     * Метод поиска заявки по id
     * @param id уникальный ключ.
     */
    protected Item findById(String id) {
        ArrayList<Item> result = new ArrayList<>();
        result.add(0, null);
            items.stream()
                    .filter(item -> item.getId().equals(id))
                    .forEach(item -> result.set(0, item));
        return result.get(0);
    }

    /**
     * Метод поиска заявки по имени
     * @param key искомое имя.
     */
    protected List<Item> findByName(String key) {
        List<Item> result = new ArrayList<Item>();
        items.stream()
                .filter(item -> item.getName().equals(key))
                .forEach(result::add);
        return result;
    }

    /**
     * Метод замены заявки по коду
     * @param id код заменяемой заявки.
     */
    public void replace(String id, Item item) {
        items.stream()
                .filter(itemRe -> itemRe.getId().equals(id))
                .peek(itemRe -> item.setId(itemRe.getId()))
                .forEach(itemRe -> items.set(items.indexOf(itemRe), item));
    }

    /**
     * Метод удаляет заявку по коду
     * @param id код удаляемой заявки.
     */
    public boolean delete(String id) {
        boolean result = items.stream()
                .anyMatch(item -> item.getId().equals(id));
        items.removeIf(item -> item.getId().equals(id));
        return result;
    }

    /**
     * Метод генерирует уникальный ключ для заявки.
     * Так как у заявки нет уникальности полей, имени и описание. Для идентификации нам нужен уникальный ключ.
     * @return Уникальный ключ.
     */
    private String generateId() {
        return String.valueOf(System.currentTimeMillis() + RN.nextInt());
    }

    /**
     * Метод возвращает копию массива this.items без null элементов
     */
    public List<Item> findAll() {
        return items;
    }
}