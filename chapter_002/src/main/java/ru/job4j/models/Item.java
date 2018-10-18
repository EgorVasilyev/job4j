package ru.job4j.models;

public class Item {
    private String id;
    public String name;
    public  String  description;
    public  String  comment;
    public long create;
    public Item() {
    }
    public Item(String name, String description, String comment, long create) {
        this.name = name;
        this.description = description;
        this.comment = comment;
        this.create = create;
    }
    public String getName() {
        return this.name;
    }
    public String getDescription() {
        return this.description;
    }
    public String getComment() {
        return this.comment;
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
}
