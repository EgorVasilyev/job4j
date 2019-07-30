package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Egor Vasilyev(deseng_pgs@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class MaxTest {
    @Test
    public void whenFirstLessSecond() {
        Max maxim = new Max();
        int result = maxim.max(-10, 223);
        assertThat(result, is(223));
    }
    @Test
    public void whenThirdMoreThenSecondAndMoreThenFirst() {
        Max maximThree = new Max();
        int result = maximThree.maxThree(-10, 22, 60);
        assertThat(result, is(60));
    }
}