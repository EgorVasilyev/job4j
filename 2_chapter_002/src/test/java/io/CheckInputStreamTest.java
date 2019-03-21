package io;

import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CheckInputStreamTest {
    @Test
    public void whenEvenThenTrue() throws IOException {
        ByteArrayInputStream stream = new ByteArrayInputStream("123456789123456789123456788".getBytes());
        assertTrue(CheckInputStream.isEvenNumber(stream));
    }
    @Test
    public void whenNotEvenThenFalse() throws IOException {
        ByteArrayInputStream stream = new ByteArrayInputStream("123456789123456789123456789".getBytes());
        assertFalse(CheckInputStream.isEvenNumber(stream));
    }
    @Test
    public void whenNotNumberThenFalse() throws IOException {
        ByteArrayInputStream stream = new ByteArrayInputStream("1234567aaaaaaaaa123456788".getBytes());
        assertFalse(CheckInputStream.isEvenNumber(stream));
    }
}
