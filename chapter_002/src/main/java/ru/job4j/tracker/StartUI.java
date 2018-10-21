package ru.job4j.tracker;
import ru.job4j.models.*;
import ru.job4j.start.*;

import java.sql.SQLOutput;

/**
 * @version 1
 * @since 22/10/2018
 */
public class StartUI {
    /**
     * Константа меню для добавления новой заявки.
     */
    private static final String ADD = "0";

    /**
     * Константа меню для отображения всех заявок.
     */
    private static final String SHOW = "1";

    /**
     * Константа меню для редактирования заявки.
     */
    private static final String EDIT = "2";

    /**
     * Константа меню для удаления заявки.
     */
    private static final String DEL = "3";

    /**
     * Константа меню для поиска заявки по ID.
     */
    private static final String FINDID = "4";

    /**
     * Константа меню для поиска заявки по имени.
     */
    private static final String FINDNAME = "5";

    /**
     * Константа для выхода из цикла.
     */
    private static final String EXIT = "6";
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
     * @param input ввод данных.
     * @param tracker хранилище заявок.
     */
    public StartUI(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Основой цикл программы.
     */
    public void init() {
        boolean exit = false;
        while (!exit) {
            this.showMenu();
            String answer = this.input.ask("Введите пункт меню : ");
            if (ADD.equals(answer)) {
                this.createItem();
            } else if (SHOW.equals(answer)) {
                this.findAllItems();
            } else if (EDIT.equals(answer)) {
                this.editItem();
            } else if (DEL.equals(answer)) {
                this.deleteItem();
            } else if (FINDID.equals(answer)) {
                this.findItemById();
            } else if (FINDNAME.equals(answer)) {
                this.findItemByName();
            } else if (EXIT.equals(answer)) {
                exit = true;
            }
        }
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void createItem() {
        System.out.println("------------ Добавление новой заявки --------------");
        String name = this.input.ask("Введите имя заявки :");
        String desc = this.input.ask("Введите описание заявки :");
        Item item = new Item(name, desc);
        this.tracker.add(item);
        System.out.println("------------ Новая заявка с ID " + item.getId() + " добавлена.-----------");
    }

    /**
     * Метод реализует добавленяи новый заявки в хранилище.
     */
    private void findAllItems() {
        Item[] result = this.tracker.findAll();
        int count1 = 0;
        for (int index = 0; index != result.length; index++) {
            count1++;
        }
        if (count1 != 0) {
            String count2 = Integer.toString(count1);
            String count3 = count2.substring(count2.length() - 1);
            int countOfItems = Integer.parseInt(count3);

            String zayavka;
            if (countOfItems == 1) {
                zayavka = " заявка ";
            } else if (countOfItems > 0 && countOfItems < 5 && (count1 < 5 || count1 > 14)) {
                zayavka = " заявки ";
            } else {
                zayavka = " заявок ";
            }
            System.out.println("------------ Найдено: " + count1 + zayavka + "-----------");
            for (int index = 0; index != result.length; index++) {
                System.out.println("-Заявка c ID: " + result[index].getId());
                System.out.println("-Имя: " + result[index].getName());
                System.out.println("-Описание: " + result[index].getDescription());
                System.out.println(" ");
            }
            System.out.println("------------------------------------------");
        } else {
            System.out.println("------------ К сожалению, заявок пока не создано. -----------");
        }
    }

    /**
     * Метод реализует изменение заявки.
     */
    private void editItem() {
        System.out.println("------------ Изменение заявки --------------");
        String id = this.input.ask("Введите ID изменяемой заявки :");
        if (this.tracker.findById(id) != null) {
            System.out.println("Изменить имя заявки?");
            String yesNo = this.input.ask("1-да, 2-нет");
            while (!yesNo.equals("1") && !yesNo.equals("2")) {
                System.out.println("Введите корректное значение:");
                yesNo = this.input.ask("1-да, 2-нет");
            }
            String name;
            if (yesNo.equals("1")) {
                name = this.input.ask("Введите новое имя заявки :");
            } else {
                name = this.tracker.findById(id).getName();
            }
            System.out.println("Изменить описание заявки?");
            String noYes = this.input.ask("1-да, 2-нет");

            while (!noYes.equals("1") && !noYes.equals("2")) {
                System.out.println("Введите корректное значение:");
                noYes = this.input.ask("1-да, 2-нет");
            }
            String desc;
            if (noYes.equals("1")) {
                desc = this.input.ask("Введите новое описание заявки :");
            } else {
                desc = this.tracker.findById(id).getDescription();
            }
            Item item = new Item(name, desc);
            this.tracker.replace(id, item);
            if (yesNo.equals("2") && noYes.equals("2")) {
                System.out.println("------------ Заявка с getId : " + item.getId() + " не изменена. -----------");
            } else {
                System.out.println("------------ Заявка с getId : " + item.getId() + " изменена. -----------");
            }
        } else {
            System.out.println("------------ К сожалению, заявка с ID " + id + " не найдена. -----------");
        }
    }


    /**
     * Метод удаляет заявку.
     */
    private void deleteItem() {
        System.out.println("------------ Удаление заявки --------------");
        String id = this.input.ask("Введите ID удаляемой заявки :");
        if (this.tracker.findById(id) != null) {
            this.tracker.delete(id);
            System.out.println("------------ Заявка с getId : " + id + " удалена -----------");
        } else {
            System.out.println("------------ К сожалению, заявка с ID " + id + " не найдена. -----------");
        }
    }

    /**
     * Метод ищет заявку по ID.
     */
    private void findItemById() {
        System.out.println("------------ Поиск заявки по ID --------------");
        String id = this.input.ask("Введите ID искомой заявки :");
        if (this.tracker.findById(id) != null) {
            System.out.println("------------ Заявка с getId : " + id + " найдена. -----------");
            System.out.println("-Имя: " + this.tracker.findById(id).getName());
            System.out.println("-Описание: " + this.tracker.findById(id).getDescription());
            System.out.println("---------------------------------------");
        } else {
            System.out.println("------------ К сожалению, заявка с ID " + id + " не найдена. -----------");
        }
    }

    /**
     * Метод ищет заявку по имени.
     */
    private void findItemByName() {
        System.out.println("------------ Поиск заявки по имени --------------");
        String name = this.input.ask("Введите имя искомой заявки :");

        Item[] result = this.tracker.findByName(name);

        int count1 = 0;
        for (int index = 0; index != result.length; index++) {
            count1++;
        }
        if (count1 != 0) {
            String count2 = Integer.toString(count1);
            String count3 = count2.substring(count2.length() - 1);
            int countOfItems = Integer.parseInt(count3);

            String zayavka;
            if (countOfItems == 1) {
                zayavka = " заявка ";
            } else if (countOfItems > 0 && countOfItems < 5 && (count1 < 5 || count1 > 14)) {
                zayavka = " заявки ";
            } else {
                zayavka = " заявок ";
            }
            System.out.println("------------ Найдено: " + count1 + zayavka + "с имененем " + name + "-----------");
            for (int index = 0; index != result.length; index++) {
                System.out.println("-Заявка c ID: " + result[index].getId());
                System.out.println("-Описание: " + result[index].getDescription());
                System.out.println(" ");
            }
            System.out.println("---------------------------------------");
        } else {
            System.out.println("------------ К сожалению, заявка с именем " + name + " не найдена. -----------");
        }
    }

    private void showMenu() {
        System.out.println("Меню.");
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
    }

    /**
     * Запуск программы.
     */
    public static void main(String[] args) {
        new StartUI(new ConsoleInput(), new Tracker()).init();
    }
}