package ru.job4j;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortArrayTest {
    @Test
    public void check() {
        SortArray sa = new SortArray();
        int[] a = new int[]{1, 10, 11, 12, 13};
        int[] b = new int[]{2, 3, 9};
        int[] result = sa.makeSortArray(a, b);
        int[] c = new int[]{1, 2, 3, 9, 10, 11, 12, 13};
        assertThat(result, is(c));
    }

}
