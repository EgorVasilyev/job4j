package ru.job4j.tracker;
import ru.job4j.models.*;

import java.util.Arrays;
import java.util.Random;

/**
 * @version 1
 * @since 18/10/2018
 */
public class Tracker {
    private static final Random RN = new Random();
    /**
     * Массив для хранение заявок.
     */
    private final Item[] items = new Item[100];

    /**
     * Указатель ячейки для новой заявки.
     */
    private int position = 0;

    /**
     * Метод реализаущий добавление заявки в хранилище
     * @param item новая заявка
     */
    public Item add(Item item) {
        item.setId(this.generateId());
        this.items[this.position++] = item;
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
    protected Item[] findByName(String key) {
        int number = 0;
        Item[] result = new Item[this.position];
        for (int index = 0; index != this.position; index++) {
            if (items[index].getName().equals(key)) {
                result[number] = items[index];
                number++;
            }
        }
        return Arrays.copyOf(result, number);

    }

    /**
     * Метод замены заявки по коду
     * @param id код заменяемой заявки.
     */
    public void replace(String id, Item item) {

        for (int index = 0; index != this.position; index++) {
            if (this.items[index] != null && this.items[index].getId().equals(id)) {
                String temp = this.items[index].getId();
                this.items[index] = item;
                this.items[index].setId(temp);
                break;
            }
        }
    }

    /**
     * Метод удаляет заявку по коду
     * @param id код удаляемой заявки.
     */
    public void delete(String id) {
        boolean removal = false;
        for (int index = 0; index != this.position; index++) {
            if (this.items[index] != null && this.items[index].getId().equals(id)) {
                System.arraycopy(items, index + 1, items, index, items.length - index - 1);
                removal = true;
            }
        }
        if (removal) {
            position--;
        }
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
    public Item[] findAll() {
        return Arrays.copyOf(this.items, this.position);
    }
}