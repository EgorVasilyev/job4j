package ru.job4j.sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class StringsCompare implements Comparator<String> {
    @Override
    public int compare(String left, String right) {

        int min = Math.min(left.length(), right.length());

        int result = 0;

        List<Character> charsLeft = new ArrayList<Character>();
        for (int index = 0; index < left.length(); index++) {
            charsLeft.add(left.charAt(index));
        }

        List<Character> charsRight = new ArrayList<Character>();
        for (int index = 0; index < right.length(); index++) {
            charsRight.add(right.charAt(index));
        }
        int i = 0;

        while (i < min) {
            result = Character.compare(charsLeft.get(i), charsRight.get(i));
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