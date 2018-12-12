package list;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {

    private SimpleStack<Integer> linkedList;

    @Test(expected = NoSuchElementException.class)
    public void whenAddThreeElementsAndGetFourElementThenNoSuchElemException() {
        linkedList = new SimpleStack<>();
        linkedList.push(1);
        linkedList.push(2);
        linkedList.push(3);
        assertThat(linkedList.poll(), is(3));
        assertThat(linkedList.poll(), is(2));
        assertThat(linkedList.poll(), is(1));
        linkedList.poll();
    }
}