package ru.job4j.labmda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Asd {
    public static void main(String[] args) {
        int[] input = {1, 2};
        List<Integer> a = new ArrayList<>();
                Arrays.stream(input).forEach(a::add);
        System.out.println(a);
    }
}
