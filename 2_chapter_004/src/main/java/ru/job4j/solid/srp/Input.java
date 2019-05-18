package ru.job4j.solid.srp;

public interface Input {
    String next();
    String askString(String question);
    double askDouble(String valueIn) throws NumberFormatException;
}
