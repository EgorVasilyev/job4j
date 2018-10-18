package ru.job4j.inheritance;

public class Teacher extends Profession {
    private String subject;
    public Teacher(String name, int age, String education) {
        super(name, age, education);
    }
    public void expelFromAudience(Student student) {
    }
}
