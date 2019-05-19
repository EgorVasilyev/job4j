package ru.job4j.solid.lsp.storages;

import ru.job4j.solid.lsp.food.Food;
import ru.job4j.solid.lsp.handler.Strategy;

public class Trash extends FoodStorage {
    public Trash(String name) {
        super(name);
        this.setStrategy(new AddToTrash());
    }
    public class AddToTrash implements Strategy {
        @Override
        public boolean condition(int percent) {
            return percent >= 100;
        }
        @Override
        public void addProcess(Food food) {
            Trash.this.add(food);
            System.out.println(food.getName() + " направлен в " + Trash.this.getName());
        }
    }
}
