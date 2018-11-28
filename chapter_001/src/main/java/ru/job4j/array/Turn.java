package ru.job4j.array;

/**
 * @author Egor Vasilyev (deseng_pgs@mail.ru)
 * @version $Id$
 * @since 0.1
 */

public class Turn {
    /**
     * Метод переворачивает массив.
     *
     * @param array Массив.
     * @return результат переворота.
     */
    public int[] turn(int[] array) {
        for (int index = 0; index < array.length / 2; index++) {
            int temp = array[array.length - index - 1];
            array[array.length - index - 1] = array[index];
            array[index] = temp;
        }
        return array;
    }
}