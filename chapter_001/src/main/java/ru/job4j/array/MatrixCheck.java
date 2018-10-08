package ru.job4j.array;
/**
 * @author Egor Vasilyev (deseng_pgs@mail.ru)
 * @version 3
 * @since 06/10/2018
 */
public class MatrixCheck {
    /**
     * проверяет равенство ячеек диагоналей двумерного массива.
     * @param array входной массив.
     * @return результат.
     */
    public boolean check(boolean[][] array) {
        boolean result = true;
        for (int i = 0; i < array.length; i++) {
            if (array[0][0] != array[i][i]) {
                result = false;
                break;
            }

            if (array[0][array.length - 1] != array[i][array.length - 1 - i]) {
                result = false;
                break;
            }
        } return result;
    }
}