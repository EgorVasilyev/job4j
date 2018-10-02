package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Egor Vasilyev (deseng_pgs@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class FindLoopTest {
    @Test
    public void whenArrayHasLengh6Then2() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 6, 3};
        int value = 6;
        int result = find.indexOf(input, value);
        int expect = 1;
        assertThat(result, is(expect));
    }
    @Test
    public void whenArrayHasLenghAnyThenMinusOne() {
        FindLoop find = new FindLoop();
        int[] input = new int[] {5, 6, 3};
        int value = 28;
        int result = find.indexOf(input, value);
        int expect = -1;
        assertThat(result, is(expect));
    }
}