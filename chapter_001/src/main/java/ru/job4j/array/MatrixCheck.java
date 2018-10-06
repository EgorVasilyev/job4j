package ru.job4j.array;
/**
 * @author Egor Vasilyev (deseng_pgs@mail.ru)
 * @version 1
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
        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array[i].length - 1; j++) {
                boolean lastInArray = array[array.length - 1][array[i].length - 1]; //последний элемент массива.
                boolean lastINi0 = array[0][array[i].length - 1]; //последний элемент первой строки.
                boolean lastINj0 = array[array.length - 1][0]; //последний элемент первого столбца.
                boolean senondDiag = array[i][array[i].length - 1 - i]; //ячейка второй диагонали кроме lastINj0.
                //проверяем первую диагональ.
                //если ячейки первой диагонали не равны, то false и break.
                if (j == i
                        && (array[i][j] != array[i + 1][j + 1]
                        || array[i][j] != lastInArray)) {
                    result = false;
                    break;
                }
                //проверяем вторую диагональ.
                //если ячейки второй диагонали не равны lastInArray, то false и break.
                if (j == array[i].length - 1 - i
                        && (lastInArray != senondDiag
                        || lastInArray != lastINj0
                        || lastInArray != lastINi0)) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }
}