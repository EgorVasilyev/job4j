package set;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SimpleSetTest {

    private SimpleSet<Integer> simpleSetList;

    @Before
    public void beforeTest() {
        simpleSetList = new SimpleSet<>();
        simpleSetList.add(1);
        simpleSetList.add(2);
        simpleSetList.add(3);
    }

    @Test
    public void whenAddFourElementThenAddTrue() {
        assertThat(simpleSetList.add(4), is(true));
    }

    @Test
    public void whenAddFourSameElementThenAddFalse() {
        assertThat(simpleSetList.add(3), is(false));
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenAddFourElementAfterCreateIteratorThenExeption() {
        Iterator<Integer> it = simpleSetList.iterator();
        assertThat(it.next(), is(1));
        simpleSetList.add(4);
        it.next();
    }
}