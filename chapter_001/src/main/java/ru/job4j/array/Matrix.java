package ru.job4j.array;

/**
 * @author Egor Vasilyev (deseng_pgs@mail.ru)
 * @version 1
 * @since 06/10/2018
 */
public class Matrix {
    /**
     * строит таблицу умножения.
     *
     * @param size размер таблицы.
     * @return результат.
     */
    public int[][] multiple(int size) {
        int[][] table = new int[size][size];
        for (int j = 0; j < size; j++) {
            for (int i = 0; i < size; i++) {
                table[i][j] = (i + 1) * (j + 1);
            }
        }
        return table;
    }
}
