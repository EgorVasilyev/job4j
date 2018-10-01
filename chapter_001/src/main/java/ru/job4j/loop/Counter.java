package ru.job4j.loop;

public class Counter {
    /**Метод возвращает сумму всех чётных значений от start до finish.
     @param start Начальное значение.
     @param finish Конечное значение.
     @return Сумма.
     */
    public int add(int start, int finish) {
        int sum = 0;
        for (int x = start; x <= finish; x++) {
            if (x % 2 == 0) {
                sum += x;
            }
        }
        return sum;
    }
}

