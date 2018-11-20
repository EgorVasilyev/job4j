package ru.job4j.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Egor Vasilyev
 * @version 1
 * @since 09/11/2018
 */

public class ConvertList {
    public List<Integer> convert(List<int[]> list) {
        List<Integer> result = new ArrayList<>();
        list.forEach(
                x -> Arrays.stream(x).forEach(result::add));
        return result;
    }
}
