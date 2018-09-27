package ru.job4j.converter;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConverterTest {
    @Test
    public void when130RubleToDollarThen2() {
        Converter converter = new Converter();
        int result = converter.rubleToDollar(130);
        assertThat(result, is(2));
    }

    @Test
    public void when228RubleToEuroThen3() {
        Converter converter = new Converter();
        int result = converter.rubleToEuro(228);
        assertThat(result, is(3));
    }
    @Test

    public void when5EuroToRubleThen380() {
        Converter converter = new Converter();
        int result = converter.euroToRuble(5);
        assertThat(result, is(380));
    }

    @Test
    public void when1DollarToRubleThen65() {
        Converter converter = new Converter();
        int result = converter.dollarToRuble(1);
        assertThat(result, is(65));
    }
}