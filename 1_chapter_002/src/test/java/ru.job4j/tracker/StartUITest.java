package ru.job4j.tracker;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.models.Item;
import ru.job4j.start.Input;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class StartUITest {

    // поле содержит дефолтный вывод в консоль.
    private final PrintStream stdout = System.out;
    // буфер для результата.
    private final ByteArrayOutputStream out = new ByteArrayOutputStream();

    private String menuForTest = new StringBuilder()
            .append("0. Добавление новой заявки.")
            .append(System.lineSeparator())
            .append("1. Показать все заявки.")
            .append(System.lineSeparator())
            .append("2. Редактрировать заявку.")
            .append(System.lineSeparator())
            .append("3. Удалить заявку.")
            .append(System.lineSeparator())
            .append("4. Поиск заявки по ID.")
            .append(System.lineSeparator())
            .append("5. Поиск заявки по имени.")
            .append(System.lineSeparator())
            .append("6. Выход.")
            .append(System.lineSeparator())
            .toString();

    @Before
    public void loadOutput() {
        System.setOut(new PrintStream(this.out));
    }

    @After
    public void backOutput() {
        System.setOut(this.stdout);
    }

    @Test
    public void whenUserFindAllItems() {
        ITracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test name 1", "desc 1"));
        Item item2 = tracker.add(new Item("test name 2", "desc 2"));
        Input input = new StubInput(new String[]{"1", "6"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(this.out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menuForTest)
                                .append("------------ Найдено: 2 заявки -----------")
                                .append(System.lineSeparator())
                                .append("-ID: " + item1.getId())
                                .append(System.lineSeparator())
                                .append("-Имя: " + item1.getName())
                                .append(System.lineSeparator())
                                .append("-Описание: " + item1.getDescription())
                                .append(System.lineSeparator())
                                .append("-Дата создания(год/месяц/число): " + item2.getCreate())
                                .append(System.lineSeparator())
                                .append(" " + System.lineSeparator())
                                .append("-ID: " + item2.getId())
                                .append(System.lineSeparator())
                                .append("-Имя: " + item2.getName())
                                .append(System.lineSeparator())
                                .append("-Описание: " + item2.getDescription())
                                .append(System.lineSeparator())
                                .append("-Дата создания(год/месяц/число): " + item2.getCreate())
                                .append(System.lineSeparator())
                                .append(" " + System.lineSeparator())
                                .append("------------------------------------------")
                                .append(System.lineSeparator())
                                .append(menuForTest)
                                .toString()
                )
        );
    }

    @Test
    public void whenUserFindByName() {
        ITracker tracker = new Tracker();
        Item item1 = tracker.add(new Item("test name", "desc 1"));
        Item item2 = tracker.add(new Item("test name", "desc 2"));
        Input input = new StubInput(new String[]{"5", "test name", "6"});
        new StartUI(input, tracker).init();
        assertThat(
                new String(this.out.toByteArray()),
                is(
                        new StringBuilder()
                                .append(menuForTest)
                                .append("------------ Поиск заявки по имени --------------")
                                .append(System.lineSeparator())
                                .append("------------ Найдено: 2 заявки с имененем test name-----------")
                                .append(System.lineSeparator())
                                .append("-ID: " + item1.getId())
                                .append(System.lineSeparator())
                                .append("-Имя: " + item1.getName())
                                .append(System.lineSeparator())
                                .append("-Описание: " + item1.getDescription())
                                .append(System.lineSeparator())
                                .append("-Дата создания(год/месяц/число): " + item2.getCreate())
                                .append(System.lineSeparator())
                                .append(" " + System.lineSeparator())
                                .append("-ID: " + item2.getId())
                                .append(System.lineSeparator())
                                .append("-Имя: " + item2.getName())
                                .append(System.lineSeparator())
                                .append("-Описание: " + item2.getDescription())
                                .append(System.lineSeparator())
                                .append("-Дата создания(год/месяц/число): " + item2.getCreate())
                                .append(System.lineSeparator())
                                .append(" " + System.lineSeparator())
                                .append("--------------------------------------------------------------------------------")
                                .append(System.lineSeparator())
                                .append(menuForTest)
                                .toString()
                )
        );
    }
}