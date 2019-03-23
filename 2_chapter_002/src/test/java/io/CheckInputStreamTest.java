package io;

import org.junit.Test;

import java.io.*;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

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
    @Test
    public void whenStreamHasBadWordsThenTheseWordMustBeDeleted() {
        String checkableString = "word1 word2 badWord word3badWord word4 terribleWord word5";

        ByteArrayInputStream input = new ByteArrayInputStream(checkableString.getBytes());
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        CheckInputStream.dropAbuses(input, output, new String[]{"badWord", "terribleWord"});

        assertThat(output.toString(), is("word1 word2 word3 word4 word5 "));
    }
}