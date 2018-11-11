package ru.job4j.sort;

import java.util.List;

public class User implements Comparable<User> {
    private Integer age;
    private String name;
    public User(Integer age, String name) {
        this.age = age;
        this.name = name;
    }
    public Integer getAge() {
        return age;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + " " + age;
    }
    @Override
    public int compareTo(User o) {
        return this.age.compareTo(o.age);
    }
}
