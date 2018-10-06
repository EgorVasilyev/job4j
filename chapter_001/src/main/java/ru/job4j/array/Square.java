package ru.job4j.array;

/**
 * @author Egor Vasilyev (deseng_pgs@mail.ru)
 * @version $Id$
 * @since 0.1
 */

public class Square {
    /**
     * заполняет массив через цикл элементами от 1 до bound, возведенными в квадрат.
     * @param bound число.
     * @return результат.
     */
    public int[] calculate(int bound) {
        int[] rst = new int[bound];
        for (int i = 0; i < bound; i++) {
            rst[i] = (i+1)*(i+1);
        }
        return rst;
    }
}