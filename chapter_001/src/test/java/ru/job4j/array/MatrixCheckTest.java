package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Egor Vasilyev (deseng_pgs@mail.ru)
 * @version 1
 * @since 06/10/2018
 */
public class MatrixCheckTest {
    @Test
    public void when4on4true() {
        MatrixCheck matrix = new MatrixCheck();
        boolean[][] array =
                {{true, false, false, true},
                        {false, true, true, false},
                        {false, true, true, false},
                        {true, false, false, true}};
        boolean result = matrix.check(array);
        assertThat(result, is(true));
    }
    @Test
    public void when3on3true() {
        MatrixCheck matrix = new MatrixCheck();
        boolean[][] array =
                {{true, false, true},
                        {false, true, false},
                        {true, true, true}};
        boolean result = matrix.check(array);
        assertThat(result, is(true));
    }
    @Test
    public void when4on4false() {
        MatrixCheck matrix = new MatrixCheck();
        boolean[][] array =
                {{true, false, false, false},
                        {false, true, true, false},
                        {false, true, true, false},
                        {true, false, false, true}};
        boolean result = matrix.check(array);
        assertThat(result, is(false));
    }
    @Test
    public void when3on3false() {
        MatrixCheck matrix = new MatrixCheck();
        boolean[][] array =
                {{true, false, true},
                        {false, true, false},
                        {true, true, false}};
        boolean result = matrix.check(array);
        assertThat(result, is(false));
    }
}