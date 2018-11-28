package ru.job4j.list;
import java.util.Arrays;
import java.util.List;

public class ConvertList2Array {
    public int[][] toArray(List<Integer> list, int rows) {
        int size = (int) list.stream().count();
        int cells = (size / rows) + 1;
        if (size % rows == 0) {
            cells = size / rows;
        }
        int[][] array = new int[rows][cells];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cells; j++) {
                if ((cells * i + j) < size) {
                    array[i][j] = list.get(cells * i + j);
                } else {
                    array[i][j] = 0;
                }
            }
        }
        return array;
    }
}