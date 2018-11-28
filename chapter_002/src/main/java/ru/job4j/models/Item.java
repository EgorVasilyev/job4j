package ru.job4j.models;

public class Item {
    private String id;
    public String name;
    public String description;
    public long create;

    public Item() {
    }

    public Item(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public long getCreate() {
        return this.create;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String toString() {
        String idString = "-ID: " + id;
        String nameString = "-Имя: " + name;
        String descString = "-Описание: " + description;

        return idString + System.lineSeparator()
                + nameString + System.lineSeparator()
                + descString;
    }
}
