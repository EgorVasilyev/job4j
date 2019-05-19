package ru.job4j.solid.lsp.handler;

import ru.job4j.solid.lsp.food.Food;
import ru.job4j.solid.lsp.food.Milk;
import ru.job4j.solid.lsp.storages.FoodStorage;
import ru.job4j.solid.lsp.storages.Shop;
import ru.job4j.solid.lsp.storages.Trash;
import ru.job4j.solid.lsp.storages.Warehouse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ControlQuality {
    private FoodStorage foodStorage;

    public ControlQuality(FoodStorage foodStorage) {
        this.foodStorage = foodStorage;
    }

    public void add(Food food) {
        Date create = food.getCreateDate();
        Date now = new Date();
        Date expire = food.getExpireDate();
        long differentExAndCr = expire.getTime() - create.getTime();
        long differentNowAndCr = now.getTime() - create.getTime();
        int percent = (int) (100 * ((double) differentNowAndCr / (double) differentExAndCr));
        System.out.println(String.format("Процент израсходования срока годности для %s: %s%s", food.getName(), percent, "%"));
        this.tryAdd(this.foodStorage, food, percent);
        System.out.println();
    }

    private void tryAdd(FoodStorage foodStorage, Food food, int percent) {
        if (foodStorage.getStrategy().condition(percent)) {
            foodStorage.getStrategy().addProcess(food);
        } else {
            if (foodStorage.getNextStorage() != null) {
                this.tryAdd(foodStorage.getNextStorage(), food, percent);
            }
        }
    }

    public static void main(String[] args) throws ParseException {
        Food milkBad = new Milk("milkBad",
                new SimpleDateFormat("d MM yyyy").parse("01 02 2019"),
                new SimpleDateFormat("d MM yyyy").parse("17 04 2019"), 100, 0);
        Food milkOld = new Milk("milkOld",
                new SimpleDateFormat("d MM yyyy").parse("01 01 2019"),
                new SimpleDateFormat("d MM yyyy").parse("23 06 2019"), 100, 0);
        Food milkNorm = new Milk("milkNorm",
                new SimpleDateFormat("d MM yyyy").parse("20 04 2019"),
                new SimpleDateFormat("d MM yyyy").parse("20 06 2019"), 100, 0);
        Food milkNew = new Milk("milkNew",
                new SimpleDateFormat("d MM yyyy").parse("17 05 2019"),
                new SimpleDateFormat("d MM yyyy").parse("08 06 2019"), 100, 0);

        Trash trash = new Trash("trash");
        Shop shop = new Shop("shop", trash);
        Warehouse warehouse = new Warehouse("warehouse", shop);

        ControlQuality controlQuality = new ControlQuality(warehouse);

        controlQuality.add(milkBad);
        controlQuality.add(milkOld);
        controlQuality.add(milkNorm);
        controlQuality.add(milkNew);
        System.out.println(warehouse.getName() + " " + warehouse.getFoodList());
        System.out.println(shop.getName() + " " + shop.getFoodList());
        System.out.println(trash.getName() + " " + trash.getFoodList());
    }
}
