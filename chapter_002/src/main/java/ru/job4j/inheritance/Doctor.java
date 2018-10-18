package ru.job4j.inheritance;

public class Doctor extends Profession {
    public Doctor(String name, int age, String education) {
        super(name, age, education);
    }
    public void diagnose(Pacient pacient) {
    }
}
