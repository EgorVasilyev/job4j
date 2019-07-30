package ru.job4j.loop;

/**
 * @author Egor Vasilyev (deseng_pgs@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class Factorial {
    /**
     * Метод возвращает факториал указанного числа.
     *
     * @param number Число.
     * @return Факториал.
     */
    public int calc(int number) {
        int rslt = 1;
        if (number >= 0) {
            for (int x = 1; x <= number; x++) {
                rslt *= x;
            }
        }
        return rslt;
    }
}
