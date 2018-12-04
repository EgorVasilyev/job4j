package iterator;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class IteratorEvenTest {

    private IteratorEven it;

    @Before
    public void setUp() {
        it = new IteratorEven(new int[]{4, 2, 1, 3, 7, 8, 3, 9});
    }

    @Test
    public void testsThatNextMethodDoesntDependsOnPriorHasNextInvocation() {
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(8));
    }

  @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.next(), is(2));
        assertThat(it.next(), is(8));
        assertThat(it.hasNext(), is(false));
    }

      @Test
    public void hasNextNextSequentialInvocation() {
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(8));
        assertThat(it.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElementException() {
        it.next();
        it.next();
        it.next();
        it.next();
    }

}