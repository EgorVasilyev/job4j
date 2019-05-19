package ru.job4j.solid.lsp.storages;

import ru.job4j.solid.lsp.food.Food;
import ru.job4j.solid.lsp.handler.Strategy;

import java.util.ArrayList;
import java.util.List;

public class FoodStorage {
    private String name;
    private List<Food> foodList;
    private Strategy strategy;
    private FoodStorage nextStorage;

    public FoodStorage(String name) {
        this.name = name;
        this.foodList = new ArrayList<>(100);
    }
    public FoodStorage(String name, FoodStorage nextStorage) {
        this(name);
        this.nextStorage = nextStorage;
    }
    public void add(Food food) {
        this.foodList.add(food);
    }
    public String getName() {
        return name;
    }
    public List<Food> getFoodList() {
        return this.foodList;
    }
    public int size() {
        return this.foodList.size();
    }
    public Strategy getStrategy() {
        return this.strategy;
    }
    protected void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
    public FoodStorage getNextStorage() {
        return this.nextStorage;
    }
}
