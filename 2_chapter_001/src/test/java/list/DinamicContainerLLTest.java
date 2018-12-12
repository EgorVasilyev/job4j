package list;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class DinamicContainerLLTest {

    private DinamicContainerLL<Integer> linkedList;

    @Test
    public void whenAddThreeElementsThenUseGetOneResultTwo() {
        linkedList = new DinamicContainerLL<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        assertThat(linkedList.get(1), is(2));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenIndexIsNotCorrect() {
        linkedList = new DinamicContainerLL<>();
        linkedList.add(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.get(3);
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddThirdElementAfterCreateIteratorThenConModExeption() {
        linkedList = new DinamicContainerLL<>();
        linkedList.add(1);
        linkedList.add(2);
        Iterator<Integer> it = linkedList.iterator();
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(1));
        linkedList.add(3);
        it.hasNext();
    }
}