package ru.job4j.labmda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

public class LabmdaFunc {

    public List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> y = new ArrayList<>();
        for (Double x = (double) start; x <= (double) end; x++) {
            y.add(func.apply(x));
        }
        return y;
    }
}


