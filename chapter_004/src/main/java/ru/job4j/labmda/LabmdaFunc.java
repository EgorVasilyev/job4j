package ru.job4j.labmda;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * @author Egor Vasilyev (deseng_pgs@mail.ru)
 * @version 2
 * @since 19.11.2018
 */

public class LabmdaFunc {
    /**
     * Метод подсчета функции в диапазоне.
     * @param start Начало диапазона.
     * @param end Конец диапазона.
     * @param func Функция.
     * @return Списочный массив со значениями функции.
     */

    public List<Double> diapason(int start, int end, Function<Double, Double> func) {
        List<Double> y = new ArrayList<>();
        for (Double x = (double) start; x <= (double) end; x++) {
            y.add(func.apply(x));
        }
        return y;
    }
}


