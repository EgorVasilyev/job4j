package ru.job4j.list;

import java.util.ArrayList;
import java.util.List;

/**
 * Egor Vasilyev
 * @version 1
 * @since 09/11/2018
 */

public class ConvertList {
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        for (int[] listValue: list) {
            for (int cellValue: listValue) {
                result.add(cellValue);
            }
        }
        return result;
    }
}
