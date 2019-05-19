package ru.job4j.solid.lsp.handler;

import ru.job4j.solid.lsp.food.Food;

public interface Strategy {
    boolean condition(int percent);
    void addProcess(Food food);
}
