package ru.job4j.array;

/**
 * @author Egor Vasilyev (deseng_pgs@mail.ru)
 * @version $Id$
 * @since 0.1
 */

public class BubbleSort {
    /**
     * Метод сортирует массив методом перестановки.
     *
     * @param array Массив.
     * @return результат перестановки.
     */
    public int[] sort(int[] array) {
        //внешний цикл прогоняет внутренний минимальное количество раз для простановки максимального
        // в конец, равное длине массива-1.
        for (int j = 0; j < array.length - 1; j++) {
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i + 1] < array[i]) {
                    int temp = array[i];
                    array[i] = array[i + 1];
                    array[i + 1] = temp;
                }
            }
        }
        return array;
    }
}