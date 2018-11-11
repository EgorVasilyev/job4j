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
     * @param хранит ссылку на списочный массив типа UserAction.
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
    public void fillActions(StartUI ui) {
        this.actions.add(new AddItem(0, "Добавление новой заявки."));
        this.actions.add(new ShowItems(1, "Показать все заявки."));
        this.actions.add(new MenuTracker.EditItem(2, "Редактрировать заявку."));
        this.actions.add(new MenuTracker.DeleteItem(3, "Удалить заявку."));
        this.actions.add(new FindItemById(4, "Поиск заявки по ID."));
        this.actions.add(new FindItemsByName(5, "Поиск заявки по имени."));
        this.actions.add(new Exit(ui, 6, "Выход."));
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
                System.out.println(action.info());
            }
        }
    }
    class AddItem extends BaseAction {

        public AddItem(int key, String name) {
            super(key, name);
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
    }
    public class ShowItems extends BaseAction {

        public ShowItems(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            List<Item> result = tracker.findAll();
            int count1 = result.size();
            if (count1 != 0) {
                String count2 = Integer.toString(count1);
                String count3 = count2.substring(count2.length() - 1);
                int countOfItems = Integer.parseInt(count3);
                String zayavka = getString(count1, countOfItems);
                System.out.println("------------ Найдено: " + count1 + zayavka + "-----------");
                for (Item item : result) {
                    System.out.println(item.toString());
                    System.out.println(" ");
                }
                System.out.println("------------------------------------------");
            } else {
                System.out.println("------------ К сожалению, заявок пока не создано. -----------");
            }
        }
    }
    public class EditItem extends BaseAction {

        public EditItem(int key, String name) {
            super(key, name);
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
    }

    public class DeleteItem extends BaseAction {

        public DeleteItem(int key, String name) {
            super(key, name);
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
    }
    public class FindItemById extends BaseAction {

        public FindItemById(int key, String name) {
            super(key, name);
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
    }

    public class FindItemsByName extends BaseAction {

        public FindItemsByName(int key, String name) {
            super(key, name);
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            System.out.println("------------ Поиск заявки по имени --------------");
            String name = input.ask("Введите имя искомой заявки :");

            List<Item> result = tracker.findByName(name);

            int count1 = result.size();
            if (count1 != 0) {
                String count2 = Integer.toString(count1);
                String count3 = count2.substring(count2.length() - 1);
                int countOfItems = Integer.parseInt(count3);
                String zayavka = getString(count1, countOfItems);

                System.out.println("------------ Найдено: " + count1 + zayavka + "с имененем " + name + "-----------");
                /*for (int index = 0; index != result.size(); index++) {
                    System.out.println(result[index].toString());
                    System.out.println(" ");
                }*/
                for (Item item : result) {
                    System.out.println(item.toString());
                    System.out.println(" ");
                }
                System.out.println("--------------------------------------------------------------------------------");
            } else {
                System.out.println("------------ К сожалению, заявка с именем " + name + " не найдена. -------------");
            }
        }
    }

    public class Exit extends BaseAction {
        private final StartUI ui;

        public Exit(StartUI ui, int key, String name) {
            super(key, name);
            this.ui = ui;
        }

        @Override
        public void execute(Input input, Tracker tracker) {
            this.ui.stop();
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