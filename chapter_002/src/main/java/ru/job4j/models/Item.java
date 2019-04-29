package ru.job4j.models;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringJoiner;

public class Item {
    private String id;
    public String name;
    public String description;
    public String create;

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
        create = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public String getCreate() {
        return this.create;
    }

    public String getId() {
        return this.id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCreate(String create) {
        this.create = create;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        String idString = "-ID: " + id;
        String nameString = "-Имя: " + name;
        String descString = "-Описание: " + description;
        String createString = "-Дата создания(год/месяц/число): " + create;
        String LN = System.lineSeparator();

        return new StringJoiner(LN).add(idString).add(nameString).add(descString).add(createString).toString();
    }
}
