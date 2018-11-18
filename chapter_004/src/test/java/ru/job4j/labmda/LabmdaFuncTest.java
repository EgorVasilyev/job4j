package ru.job4j.labmda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LabmdaFuncTest {
    @Test
    public void whenLineFunction() {
        LabmdaFunc useBy = new LabmdaFunc();
        Function<Double, Double> line = (x) -> {
            Double k, b;
            k = 1.0;
            b = 0.0;
            return k * x + b;
        };
        List<Double> lineFunc = useBy.diapason(1, 4, line);

        List<Double> result = new ArrayList<>();
        double k, b;
        k = 1.0;
        b = 0.0;
        for (double x = 1.0; x <= 4.0; x++) {
            result.add(k * x + b);
        }

        assertThat(lineFunc, is(result));
    }

    @Test
    public void whenKvadrFunction() {
        LabmdaFunc useBy = new LabmdaFunc();
        Function<Double, Double> kvadr = (x) -> {
            Double a, b, c;
            a = 1.0;
            b = 2.0;
            c = 3.0;
            return a * Math.pow(x, 2) + b * x + c;
        };
        List<Double> kvadrFunc = useBy.diapason(1, 4, kvadr);

        List<Double> result = new ArrayList<>();
        double a, b, c;
        a = 1.0;
        b = 2.0;
        c = 3.0;
        for (double x = 1.0; x <= 4.0; x++) {
            result.add(a * Math.pow(x, 2) + b * x + c);
        }

        assertThat(kvadrFunc, is(result));
    }

    @Test
    public void whenLogFunction() {
        LabmdaFunc useBy = new LabmdaFunc();
        Function<Double, Double> log = (x) -> {
            Double a;
            a = 0.1;
            return Math.log(x) / Math.log(a); //y = LOG a (X) = LN(X) / LN(a)
        };
        List<Double> logFunc = useBy.diapason(1, 4, log);

        List<Double> result = new ArrayList<>();
        double a;
        a = 0.1;
        for (double x = 1.0; x <= 4.0; x++) {
            result.add(Math.log(x) / Math.log(a));
        }

        assertThat(logFunc, is(result));
    }
}


