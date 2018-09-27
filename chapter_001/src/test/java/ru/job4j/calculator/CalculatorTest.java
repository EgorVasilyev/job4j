package ru.job4j.calculator;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CalculatorTest {
    @Test
    public void whenAddFourPlusTwoThenSix() {
        Calculator calc = new Calculator();
        calc.add(4, 2);
        double result = calc.getResult();
        double expected = 6;
        assertThat(result, is(expected));
    }
    @Test
    public void whenSubtractTwoMinusOneThenOne() {
        Calculator calc = new Calculator();
        calc.subtract(2, 1);
        double result = calc.getResult();
        double expected = 1;
        assertThat(result, is(expected));
    }
    @Test
    public void whenDivNineByThreeThenThree() {
        Calculator calc = new Calculator();
        calc.div(9, 3);
        double result = calc.getResult();
        double expected = 3;
        assertThat(result, is(expected));
    }
    @Test
    public void whenMultipleTwoToSevenThenFourteen() {
        Calculator calc = new Calculator();
        calc.multiple(2, 7);
        double result = calc.getResult();
        double expected = 14;
        assertThat(result, is(expected));
    }
}
