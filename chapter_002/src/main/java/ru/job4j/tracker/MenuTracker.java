package ru.job4j.tracker;


import ru.job4j.models.Item;
import ru.job4j.start.Input;

import java.util.ArrayList;
import java.util.List;

public class MenuTracker {
    /**
     * @param хранит ссылку на объект .
     */
    private Input input;
    /**
     * @param хранит ссылку на объект .
     */
    private Tracker tracker;
    /**
     * @param хранит ссылку на массив типа UserAction.
     */
    private List<UserAction> actions = new ArrayList<>();

    /**
     * Конструктор.
     *
     * @param input   объект типа Input
     * @param tracker объект типа Tracker
     */
    public MenuTracker(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Метод для получения массива меню.
     *
     * @return длину массива
     */
    public int getActionsLentgh() {
        return this.actions.size();
    }

    /**
     * Метод заполняет массив.
     */
    public void fillActions() {
        this.actions.add(new AddItem());
        this.actions.add(new ShowItems());
        this.actions.add(new MenuTracker.EditItem());
        this.actions.add(new MenuTracker.DeleteItem());
        this.actions.add(new FindItemById());
        this.actions.add(new FindItemsByName());
    }

    /**
     * Метод в зависимости от указанного ключа, выполняет соотвествующие действие.
     *
     * @param key ключ операции
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * Метод выводит на экран меню.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.key() + ". " + action.info());
            }
        }
    }
    public class AddItem implements UserAction {
        @Override
        public int key() {
            return 0;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Добавление новой заявки --------------");
            String name = input.ask("Введите имя заявки :");
            String desc = input.ask("Введите описание заявки :");
            Item item = new Item(name, desc);
            tracker.add(item);
            System.out.println("------------ Новая заявка с ID " + item.getId() + " добавлена.-----------");
        }

        @Override
        public String info() {
            return "Добавление новой заявки.";
        }
    }
    public class ShowItems implements UserAction {

        @Override
        public int key() {
            return 1;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            Item[] result = tracker.findAll();
            int count1 = result.length;
            if (count1 != 0) {
                String count2 = Integer.toString(count1);
                String count3 = count2.substring(count2.length() - 1);
                int countOfItems = Integer.parseInt(count3);
                String zayavka = getString(count1, countOfItems);
                System.out.println("------------ Найдено: " + count1 + zayavka + "-----------");
                for (int index = 0; index != result.length; index++) {
                    System.out.println(result[index].toString());
                    System.out.println(" ");
                }
                System.out.println("------------------------------------------");
            } else {
                System.out.println("------------ К сожалению, заявок пока не создано. -----------");
            }
        }

        @Override
        public String info() {
            return "Показать все заявки.";
        }
    }
    public class EditItem implements UserAction {

        @Override
        public int key() {
            return 2;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Изменение заявки --------------");
            String id = input.ask("Введите ID изменяемой заявки :");
            if (tracker.findById(id) != null) {
                String name = input.ask("Введите новое имя заявки :");
                String desc = input.ask("Введите новое описание заявки :");
                Item item = new Item(name, desc);
                tracker.replace(id, item);
                System.out.println("------------ Заявка с getId : " + item.getId() + " изменена. -----------");
            } else {
                System.out.println("------------ К сожалению, заявка с ID " + id + " не найдена. -----------");
            }
        }

        @Override
        public String info() {
            return "Редактрировать заявку.";
        }
    }

    public class DeleteItem implements UserAction {

        @Override
        public int key() {
            return 3;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Удаление заявки --------------");
            String id = input.ask("Введите ID удаляемой заявки :");
            if (tracker.delete(id)) {
                System.out.println("------------ Заявка с getId : " + id + " удалена -----------");
            } else {
                System.out.println("------------ К сожалению, заявка с ID " + id + " не найдена. -----------");
            }
        }

        @Override
        public String info() {
            return "Удалить заявку.";
        }
    }
    public class FindItemById implements UserAction {

        @Override
        public int key() {
            return 4;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки по ID --------------");
            String id = input.ask("Введите ID искомой заявки :");
            Item foundItem = tracker.findById(id);
            if (foundItem != null) {
                System.out.println("------------ Заявка с getId : " + id + " найдена. -----------");
                System.out.println(foundItem.toString());
                System.out.println("-------------------------------------------------------------");
            } else {
                System.out.println("------------ К сожалению, заявка с ID " + id + " не найдена. -----------");
            }
        }

        @Override
        public String info() {
            return "Поиск заявки по ID.";
        }
    }

    public class FindItemsByName implements UserAction {

        @Override
        public int key() {
            return 5;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки по имени --------------");
            String name = input.ask("Введите имя искомой заявки :");

            Item[] result = tracker.findByName(name);

            int count1 = result.length;
            if (count1 != 0) {
                String count2 = Integer.toString(count1);
                String count3 = count2.substring(count2.length() - 1);
                int countOfItems = Integer.parseInt(count3);
                String zayavka = getString(count1, countOfItems);

                System.out.println("------------ Найдено: " + count1 + zayavka + "с имененем " + name + "-----------");
                for (int index = 0; index != result.length; index++) {
                    System.out.println(result[index].toString());
                    System.out.println(" ");
                }
                System.out.println("--------------------------------------------------------------------------------");
            } else {
                System.out.println("------------ К сожалению, заявка с именем " + name + " не найдена. -------------");
            }
        }

        @Override
        public String info() {
            return "Поиск заявки по имени.";
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
}