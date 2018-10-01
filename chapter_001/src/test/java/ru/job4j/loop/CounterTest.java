package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Egor Vasilyev(deseng_pgs@mail.ru)
 * @version $Id$
 * @since 0.1
 */

public class CounterTest {
    @Test
    public void whenFirstLessSecond() {
        Counter count = new Counter();
        int result = count.add(1, 6);
        assertThat(result, is(12));
    }
}