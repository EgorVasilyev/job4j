package ru.job4j;

import java.util.*;

public class CoffeeMachine {
    private List<Integer> changesList = new ArrayList<>();
    public int[] changes(int value, int price) {
        NavigableSet<Integer> coins = new TreeSet<Integer>();
            coins.add(new Integer("1"));
            coins.add(new Integer("2"));
            coins.add(new Integer("5"));
            coins.add(new Integer("10"));
        int remainingChange = value - price;
        Iterator it = coins.descendingIterator();
        while (remainingChange != 0) {
            int coin = (int) it.next();
            int count = (remainingChange / coin);
            if (count != 0) {
                remainingChange -= addMonets(count, coin);
            }
        }
        int[] change = new int[changesList.size()];
        for (int i = 0; i < changesList.size(); i++) {
            change[i] = changesList.get(i);
        }
        return change;
    }
    private int addMonets(int count, int coin) {
        int sum = 0;
        while (count > 0) {
            changesList.add(coin);
            sum += coin;
            count--;
        }
        return sum;
    }
}