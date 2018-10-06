package ru.job4j.array;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * @author Egor Vasilyev (deseng_pgs@mail.ru)
 * @version $Id$
 * @since 0.1
 */

public class BubbleSortTest {
    @Test
    public void when697845231Then123456789() {
        int[] array = new int[]{6, 9, 7, 8, 4, 5, 2, 1, 3};
        BubbleSort bs = new BubbleSort();
        int[] rst = bs.sort(array);
        int[] expect = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        assertThat(rst, is(expect));
    }
}