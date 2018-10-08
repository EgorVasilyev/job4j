package ru.job4j.array;
import java.util.Arrays;
/**
 * @author Egor Vasilyev (deseng_pgs@mail.ru)
 * @version 1
 * @since 08.10.2018
 */

public class ArrayDuplicate {
    /**
     * Удаляет дуюликаты из массива.
     * @param array массив.
     * @return массив без дубликтов.
     */
    public String[] removeDuplicate(String[] array) {
        int unique = array.length;
        for (int out = 0; out < unique; out++) {
            for (int in = out + 1; in < unique; in++) {
                if (array[out].equals(array[in])) {
                    array[in] = array[unique - 1];
                    unique--;
                    in--;
                }
            }
        } return Arrays.copyOf(array, unique);
    }
}
