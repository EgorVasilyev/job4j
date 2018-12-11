package list;

import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DinamicContainerTest {

    private DinamicContainer<Integer> list;

    @Before
    public void beforeTest() {
        list = new DinamicContainer<>();
        list.add(1);
        list.add(2);
        list.add(3);
    }

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        assertThat(list.get(1), is(2));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddFourElementAfterCreateIteratorThenExeption() {
        Iterator<Integer> it = list.iterator();
        assertThat(it.next(), is(1));
        list.add(4);
        it.next();
    }
}