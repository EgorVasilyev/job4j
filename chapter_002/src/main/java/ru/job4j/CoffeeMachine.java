package ru.job4j;

import java.util.*;

/**
 * @author Egor Vasilyev
 * @version 1
 * @since 27/01/2019
 */

/**
 * Класс Кофемашина.
 */
public class CoffeeMachine {
    /**
     * Лист, хранящий сдачу с минимальным набором монет
     */
    private List<Integer> changesList = new ArrayList<>();
    /**
     * Метод, возвращающий массив целых чисел, соответствующих
     * минимальному набору монет, необходимому для сдачи
     *
     * @param value- внесенная пользователем купюра
     * @param price- цена выбранного напитка
     */
    public int[] changes(int value, int price) {
//Множество, хранящее целые числа (монеты номиналом 1, 2, 5, 10)
        NavigableSet<Integer> coins = new TreeSet<Integer>();
        coins.add(new Integer("1"));
        coins.add(new Integer("2"));
        coins.add(new Integer("5"));
        coins.add(new Integer("10"));
// Сдача
        int remainingChange = value - price;
//Итератор, возвращающий значения по убыванию
        Iterator it = coins.descendingIterator();
//Цикл работает, пока оставшаяся сдача remainingChange не равна нулю.
        while (remainingChange != 0) {
            int coin = (int) it.next();
//переменная count определяет, сколько раз добавить монету в changesList
            int count = (remainingChange / coin);
            if (count != 0) {
//оставшаяся сдача remainingChange уменьшается по мере добавления монет
//в лист changesList, вычитая из текущей сдачи результат метода addMonets().
                remainingChange -= addMonets(count, coin);
            }
        }
//создание массива на основе листа changesList
        int[] change = new int[changesList.size()];
        for (int i = 0; i < changesList.size(); i++) {
            change[i] = changesList.get(i);
        }
        return change;
    }
    /**
     * Метод, добавляющий монеты(целые числа из множества coins) в лист сдачи changesList
     * count раз и возвращающий сумму этих монет
     *
     * @param count- количество раз добавления актуальной монеты в лист changesList
     * @param coin- целое число из множества coins (монета с наибольшим наминалом
     * на данном этапе добавления)
     */
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