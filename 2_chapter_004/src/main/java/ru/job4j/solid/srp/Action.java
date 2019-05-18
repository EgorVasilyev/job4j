package ru.job4j.solid.srp;

public interface Action {
    String getSymbol();
    String description();
    double calculate(double first, double second) throws CalculateException;
}
