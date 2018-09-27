package ru.job4j.condition;

import org.junit.Test;
import ru.job4j.converter.Converter;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PointTest {
    @Test
    public void whenX1equallyX2andY1equallyY2thenNull() {
        Point aTest = new Point(6,3);
        Point bTest = new Point(6,3);
        double result = aTest.distanceTo(bTest);
        assertThat(result, is(0.0));
    }
    @Test
    public void DistantMustBeMoreOrEquallyThenNule() {
        Point aTest = new Point(234,-13);
        Point bTest = new Point(-10,236);
        double distantTest=aTest.distanceTo(bTest);
        boolean result =distantTest>=0;
                assertThat(result, is(true));
    }
}