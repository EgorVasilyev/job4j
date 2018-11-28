package ru.job4j.tracker;

import ru.job4j.models.Item;
import ru.job4j.start.ConsoleInput;
import ru.job4j.start.Input;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @version 2
 * @since 22/10/2018
 */
public class StartUI {
    private boolean working = true;

    /**
     * Вывод на печать.
     */
    private Consumer<String> println = System.out::println;

    /**
     * Получение данных от пользователя.
     */
    private final Input input;

    /**
     * Хранилище заявок.
     */
    private final Tracker tracker;

    /**
     * Конструтор инициализирующий поля.
     *
     * @param input   ввод данных.
     * @param tracker хранилище заявок.
     */
    private List<Integer> range = new ArrayList<Integer>();

    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        MenuTracker menu = new MenuTracker(this.input, this.tracker);
        menu.fillActions(this);

        for (int i = 0; i < menu.getActionsLentgh(); i++) {
            range.add(i);
        }
        do {

            menu.show(System.out::println);
            menu.select(input.ask("Выбирите пункт меню:", range));
        } while (this.working);
    }


    /**
     * Метод реализует добавление новый заявки в хранилище.
     */
    private void createItem() {
        println.accept("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        println.accept("------------ Новая заявка с ID " + item.getId() + " добавлена.-----------");
    }

    /**
     * Метод отображает все заявки.
     */
    private void findAllItems() {
        List<Item> result = this.tracker.findAll();
        int count1 = result.size();
        if (count1 != 0) {
            String count2 = Integer.toString(count1);
            String count3 = count2.substring(count2.length() - 1);
            int countOfItems = Integer.parseInt(count3);
            String zayavka = getString(count1, countOfItems);
            println.accept("------------ Найдено: " + count1 + zayavka + "-----------");
            for (Item item : result) {
                System.out.println(item.toString());
                System.out.println(" ");
            }
            println.accept("------------------------------------------");
        } else {
            println.accept("------------ К сожалению, заявок пока не создано. -----------");
        }
    }

    /**
     * Метод реализует изменение заявки.
     */
    private void editItem() {
        println.accept("------------ Изменение заявки --------------");
        String id = this.input.ask("Введите ID изменяемой заявки :");
        if (this.tracker.findById(id) != null) {
            String name = this.input.ask("Введите новое имя заявки :");
            String desc = this.input.ask("Введите новое описание заявки :");
            Item item = new Item(name, desc);
            this.tracker.replace(id, item);
            println.accept("------------ Заявка с getId : " + item.getId() + " изменена. -----------");
        } else {
            println.accept("------------ К сожалению, заявка с ID " + id + " не найдена. -----------");
        }
    }


    /**
     * Метод удаляет заявку.
     */
    private void deleteItem() {
        println.accept("------------ Удаление заявки --------------");
        String id = this.input.ask("Введите ID удаляемой заявки :");
        if (this.tracker.delete(id)) {
            println.accept("------------ Заявка с getId : " + id + " удалена -----------");
        } else {
            println.accept("------------ К сожалению, заявка с ID " + id + " не найдена. -----------");
        }
    }

    /**
     * Метод ищет заявку по ID.
     */
    private void findItemById() {
        println.accept("------------ Поиск заявки по ID --------------");
        String id = this.input.ask("Введите ID искомой заявки :");
        Item foundItem = this.tracker.findById(id);
        if (foundItem != null) {
            println.accept("------------ Заявка с getId : " + id + " найдена. -----------");
            println.accept(foundItem.toString());
            println.accept("-------------------------------------------------------------");
        } else {
            println.accept("------------ К сожалению, заявка с ID " + id + " не найдена. -----------");
        }
    }

    /**
     * Метод ищет заявку по имени.
     */
    private void findItemByName() {
        println.accept("------------ Поиск заявки по имени --------------");
        String name = this.input.ask("Введите имя искомой заявки :");

        List<Item> result = this.tracker.findByName(name);

        int count1 = result.size();
        if (count1 != 0) {
            String count2 = Integer.toString(count1);
            String count3 = count2.substring(count2.length() - 1);
            int countOfItems = Integer.parseInt(count3);
            String zayavka = getString(count1, countOfItems);

            println.accept("------------ Найдено: " + count1 + zayavka + "с имененем " + name + "-----------");

            for (Item item : result) {
                println.accept(item.toString());
                println.accept(" ");
            }

           /* for (int index = 0; index != result.size(); index++) {
                System.out.println(result[index].toString());
                System.out.println(" ");
            }*/
            println.accept("--------------------------------------------------------------------------------");
        } else {
            println.accept("------------ К сожалению, заявка с именем " + name + " не найдена. -------------");
        }
    }

    /**
     * Метод возвращает слово "заявка" с нужным суффикосом.
     */
    private String getString(int count1, int countOfItems) {
        String zayavka;
        if (countOfItems == 1) {
            zayavka = " заявка ";
        } else if (countOfItems > 0 && countOfItems < 5 && (count1 < 5 || count1 > 14)) {
            zayavka = " заявки ";
        } else {
            zayavka = " заявок ";
        }
        return zayavka;
    }

    public void stop() {
        this.working = false;
    }

    /**
     * Запуск программы.
     */
    public static void main(String[] args) {
        Input input2 = new ConsoleInput();
        Input input = new ValidateInput(input2);
        Tracker tracker = new Tracker();
        new StartUI(input, tracker).init();
    }

}