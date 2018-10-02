package ru.job4j.array;

/**
 * @author Egor Vasilyev (deseng_pgs@mail.ru)
 * @version $Id$
 * @since 0.1
 */

public class Check {
    /**
     * Метод проверяет, что все элементы в массиве являются true или false.
     * @param data Массив булевых значений.
     * @return результат.
     */
    public boolean mono(boolean[] data) {
        boolean result = false;
        for (int i = 0; i < data.length; i++) {
            result = data[i + 1] == data[i];
            if (i == data.length - 2 || !result) {
                break;
            }
        }
        return result;
    }
}
