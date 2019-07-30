package ru.job4j;
import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class CoffeeMachineTest {
    @Test
    public void whenValue100Price54ThenChangesIs46() {
        CoffeeMachine coffee = new CoffeeMachine();
        int[] result = coffee.changes(100, 54);
        int[] rightChanges = new int[] {10, 10, 10, 10, 5, 1};
        assertThat(result, is(rightChanges));
    }
}