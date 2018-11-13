package ru.job4j.sort;

import java.util.Comparator;

public class StringsCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {

        int min = Math.min(left.length(), right.length());

        int result = 0;

        int i = 0;

        while (i < min) {
            result = Character.compare(left.charAt(i), right.charAt(i));
            if (result != 0) {
                break;
            }
            i++;
        }
        if (i == min) {
            result = Integer.compare(left.length(), right.length());
        }
        return result;
    }
}