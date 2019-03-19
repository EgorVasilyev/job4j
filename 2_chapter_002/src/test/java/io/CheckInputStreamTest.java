package io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.Assert.*;

public class CheckInputStreamTest {
    @Test
    public void whenAllElementsAreEvenThenTrue() {
        byte[] array = new byte[]{2, 4, 6, 22};
        ByteArrayInputStream stream = new ByteArrayInputStream(array);
        try {
            assertTrue(CheckInputStream.isEvenNumber(stream));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void whenInputStreamContainNullThenFalse() {
        byte[] array = new byte[]{2, 0, 6, 22};
        ByteArrayInputStream stream = new ByteArrayInputStream(array);
        try {
            assertFalse(CheckInputStream.isEvenNumber(stream));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void whenInputStreamContainMinusOneThenFalse() {
        byte[] array = new byte[]{2, 4, 6, -1};
        ByteArrayInputStream stream = new ByteArrayInputStream(array);
        try {
            assertFalse(CheckInputStream.isEvenNumber(stream));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
