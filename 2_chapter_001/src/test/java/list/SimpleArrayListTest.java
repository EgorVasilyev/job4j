package list;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayListTest {

    private SimpleArrayList<Integer> list;

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.get(1), is(2));
    }

    @Test
    public void whenAddThreeElementsThenUseGetSizeResultThree() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.getSize(), is(3));
    }

    @Test
    public void whenAddThreeElementsAndDeleteFirstThenGetThreeAndActualFirstIsTwoAndSizeIsTwo() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        assertThat(list.delete(), is(3));
        assertThat(list.get(0), is(2));
        assertThat(list.getSize(), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenElementsAreAbsent() {
        list = new SimpleArrayList<>();
        list.delete();
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIndexIsNotCorrect() {
        list = new SimpleArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.get(3);
    }
}