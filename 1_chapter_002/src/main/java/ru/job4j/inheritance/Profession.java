package ru.job4j.inheritance;

public class Profession {
    public String name;
    public int age;
    public String education;

    public Profession() {
    }

    public Profession(String name, int age, String education) {
        this.name = name;
        this.age = age;
        this.education = education;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public String getEducation() {
        return this.education;
    }

}
