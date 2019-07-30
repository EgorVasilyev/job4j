package ru.job4j.list;

import org.junit.Test;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ConvertList2ArrayTest {
    @Test
    public void when7ElementsThen9() {
        ConvertList2Array list = new ConvertList2Array();
        int[][] result = list.toArray(
                Arrays.asList(1, 2, 3, 4, 5, 6, 7),
                3
        );
        int[][] expect = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 0, 0}
        };
        assertThat(result, is(expect));
    }
    @Test
    public void when4ElementsThenCheckByThreedimensionalArray() {
        ConvertList2Array list = new ConvertList2Array();
        for (int rows = 1; rows <= 4; rows++) {
            int[][] result = list.toArray(
                    Arrays.asList(1, 2, 3, 4),
                    rows
            );
            int[][][] expect = {
                    {{1, 2, 3, 4}},
                    {{1, 2}, {3, 4}},
                    {{1, 2}, {3, 4}, {0, 0}},
                    {{1}, {2}, {3}, {4}}
            };
            assertThat(result, is(expect[rows - 1]));
        }

    }
}