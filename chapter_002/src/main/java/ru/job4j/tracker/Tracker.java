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
        Item result = null;
        for (Item item : items) {
            if (item != null && item.getId().equals(id)) {
                result = item;
                break;
            }
        }
        return result;
    }

    /**
     * Метод поиска заявки по имени
     * @param key искомое имя.
     */
    protected List<Item> findByName(String key) {
        List<Item> result = new ArrayList<Item>();
        for (Item item : items) {
            if (item.getName().equals(key)) {
                result.add(item);
            }
        }
        return result;

    }

    /**
     * Метод замены заявки по коду
     * @param id код заменяемой заявки.
     */
    public void replace(String id, Item item) {
        for (Item itemRe : items) {
            if (itemRe != null && itemRe.getId().equals(id)) {
                item.setId(itemRe.getId());
                items.set(items.indexOf(itemRe), item);
            }
        }
    }

    /**
     * Метод удаляет заявку по коду
     * @param id код удаляемой заявки.
     */
    public boolean delete(String id) {
        Iterator<Item> iter = items.iterator();
        boolean removal = false;
        while (iter.hasNext()) {
            if (iter.next().getId().equals(id)) {
                iter.remove();
                removal = true;
            }
        }
        return removal;
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