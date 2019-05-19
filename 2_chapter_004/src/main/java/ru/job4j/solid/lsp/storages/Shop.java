package ru.job4j.solid.lsp.storages;

import ru.job4j.solid.lsp.food.Food;
import ru.job4j.solid.lsp.handler.Strategy;

public class Shop extends FoodStorage {
    public Shop(String name, FoodStorage foodStorage) {
        super(name, foodStorage);
        this.setStrategy(new AddToShop());
    }

    public class AddToShop implements Strategy {
        private int percent;
        @Override
        public boolean condition(int percent) {
            this.percent = percent;
            return percent >= 25 && percent < 100;
        }
        @Override
        public void addProcess(Food food) {
            if (this.percent < 75) {
                Shop.this.add(food);
                System.out.println(food.getName() + " направлен в " + Shop.this.getName());
            } else {
                System.out.println("Старая цена: " + food.getPrice());
                food.setDiscount(25);
                System.out.println("Новая цена: " + food.getPrice());
                Shop.this.add(food);
                System.out.println(
                        String.format("%s направлен в %s со скидкой %s%s", food.getName(), Shop.this.getName(), food.getDiscount(), "%")
                );
            }
        }
    }
}
