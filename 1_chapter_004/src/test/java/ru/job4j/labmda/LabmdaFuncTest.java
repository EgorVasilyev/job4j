package ru.job4j.labmda;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LabmdaFuncTest {
    @Test
    public void whenLineFunction() {

        LabmdaFunc useBy = new LabmdaFunc();
        List<Double> lineFunc = useBy.diapason(
                1, 4,
                (x) -> 2 * x + 3);

        List<Double> result = new ArrayList<>();
        for (double x = 1.0; x <= 4.0; x++) {
            result.add(2 * x + 3);
        }

        assertThat(lineFunc, is(result));
    }

    @Test
    public void whenKvadrFunction() {
        LabmdaFunc useBy = new LabmdaFunc();
        List<Double> kvadrFunc = useBy.diapason(
                1, 4,
                (x) -> 2 * Math.pow(x, 2) + 3 * x + 4
        );

        List<Double> result = new ArrayList<>();
        for (double x = 1.0; x <= 4.0; x++) {
            result.add(2 * Math.pow(x, 2) + 3 * x + 4);
        }

        assertThat(kvadrFunc, is(result));
    }

    @Test
    public void whenLogFunction() {
        LabmdaFunc useBy = new LabmdaFunc();

        List<Double> logFunc = useBy.diapason(
                1, 4,
                (x) -> Math.log(x) / Math.log(0.1)
        );
        //y = LOG a (X) = LN(X) / LN(a));

        List<Double> result = new ArrayList<>();
        for (double x = 1.0; x <= 4.0; x++) {
            result.add(Math.log(x) / Math.log(0.1));
        }

        assertThat(logFunc, is(result));
    }
}


