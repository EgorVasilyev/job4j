package ru.job4j.solid.lsp.storages;

import ru.job4j.solid.lsp.food.Food;
import ru.job4j.solid.lsp.handler.Strategy;

public class Warehouse extends FoodStorage {
    public Warehouse(String name, FoodStorage foodStorage) {
        super(name, foodStorage);
        this.setStrategy(new AddToWarehouse());
    }
    public class AddToWarehouse implements Strategy {
        @Override
        public boolean condition(int percent) {
            return percent < 25;
        }
        @Override
        public void addProcess(Food food) {
            Warehouse.this.add(food);
            System.out.println(food.getName() + " направлен в " + Warehouse.this.getName());
        }
    }
}
