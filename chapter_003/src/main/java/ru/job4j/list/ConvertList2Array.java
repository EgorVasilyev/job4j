package ru.job4j.list;

import java.util.Arrays;
import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int cells = (list.size() / rows) + 1;
        if (list.size() % rows == 0) {
            cells = list.size() / rows;
        }
        int[][] array = new int[rows][cells];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cells; j++) {
                if ((cells * i + j) < list.size()) {
                    array[i][j] = list.get(cells * i + j);
                } else {
                    array[i][j] = 0;
                }
            }
        }
        return array;
    }
}