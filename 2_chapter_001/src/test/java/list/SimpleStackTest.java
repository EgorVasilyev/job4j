package list;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleStackTest {

    private SimpleStack<Integer> stack;

    @Test(expected = NoSuchElementException.class)
    public void whenAddThreeElementsAndGetFourElementThenNoSuchElemException() {
        stack = new SimpleStack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertThat(stack.poll(), is(3));
        assertThat(stack.poll(), is(2));
        assertThat(stack.poll(), is(1));
        stack.poll();
    }
}