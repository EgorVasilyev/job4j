package ru.job4j.inheritance;

public class Engineer extends Profession {
    private String category;

    public Engineer(String name, int age, String education) {
        super(name, age, education);
    }

    public void build(Building building) {
    }
}
