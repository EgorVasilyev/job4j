package ru.job4j.loop;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Egor Vasilyev(deseng_pgs@mail.ru)
 * @version $Id$
 * @since 0.1
 */
public class FactorialTest {
    @Test
    public void when6Then720() {
        Factorial fac = new Factorial();
        assertThat(
                fac.calc(6),
                is(720)
        );
    }
    @Test
    public void when0Then1() {
        Factorial fac = new Factorial();
        assertThat(
                fac.calc(0),
                is(1)
        );
    }
}