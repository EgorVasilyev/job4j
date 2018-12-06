package generic;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class SimpleArrayTest {
    @Test
    public void whenAddAndGetElement() {
        SimpleArray<String> array = new SimpleArray<String>(4);
        String a, b;
        a = "test1";
        b = "test2";
        array.add(a);
        array.add(b);
        assertThat(array.get(1), is("test2"));
        assertThat(array.get(0), is("test1"));
    }

    @Test
    public void whenDelElement() {
        SimpleArray<String> array = new SimpleArray<String>(4);
        String a, b;
        a = "test1";
        b = "test2";
        array.add(a);
        array.add(b);
        array.delete(0);
        assertThat(array.get(0), is("test2"));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenCountOfElementsMoreThanSizeOfArray() {
        SimpleArray<String> array = new SimpleArray<String>(2);
        String a, b, c;
        a = "test1";
        b = "test2";
        c = "test3";
        array.add(a);
        array.add(b);
        array.add(c);
    }

    @Test
    public void whenSetElement() {
        SimpleArray<String> array = new SimpleArray<String>(2);
        String a, b, c;
        a = "test1";
        b = "test2";
        c = "test3";
        array.add(a);
        array.add(b);
        assertThat(array.get(0), is("test1"));
        array.set(0, c);
        assertThat(array.get(0), is("test3"));
    }

    @Test
    public void sequentialHasNextInvocationDoesntAffectRetrievalOrder() {
        SimpleArray<Integer> array = new SimpleArray<Integer>(3);
        array.add(4);
        array.add(1);
        array.add(2);
        assertThat(array.iterator().hasNext(), is(true));
        assertThat(array.iterator().hasNext(), is(true));
        assertThat(array.iterator().hasNext(), is(true));
        assertThat(array.iterator().next(), is(4));
        assertThat(array.iterator().next(), is(1));
        assertThat(array.iterator().next(), is(2));
        assertThat(array.iterator().hasNext(), is(false));
    }

    @Test
    public void hasNextNextSequentialInvocation() {
        SimpleArray<Integer> array = new SimpleArray<Integer>(3);
        array.add(4);
        array.add(1);
        array.add(2);
        assertThat(array.iterator().hasNext(), is(true));
        assertThat(array.iterator().next(), is(4));
        assertThat(array.iterator().hasNext(), is(true));
        assertThat(array.iterator().next(), is(1));
        assertThat(array.iterator().hasNext(), is(true));
        assertThat(array.iterator().next(), is(2));
        assertThat(array.iterator().hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void whenNoSuchElementException() {
        SimpleArray<Integer> array = new SimpleArray<Integer>(3);
        array.add(4);
        array.add(1);
        array.add(2);
        array.iterator().next();
        array.iterator().next();
        array.iterator().next();
        array.iterator().next();
    }
}
