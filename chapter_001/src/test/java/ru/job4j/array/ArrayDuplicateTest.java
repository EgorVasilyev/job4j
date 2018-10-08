package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsArrayContainingInAnyOrder.arrayContainingInAnyOrder;

public class ArrayDuplicateTest {

    @Test
    public void whenArrayHasDuplicatesIhenDropIt() {
        String[] input = {"Liza", "Olga", "Liza", "Sveta", "Valeria"};
        String[] except = {"Liza", "Olga", "Sveta", "Valeria"};
        ArrayDuplicate duplicate = new ArrayDuplicate();
        String[] result = duplicate.removeDuplicate(input);
        assertThat(result, arrayContainingInAnyOrder(except));
    }
}