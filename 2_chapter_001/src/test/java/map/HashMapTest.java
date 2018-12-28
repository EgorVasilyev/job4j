package map;
import org.junit.Before;
import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class HashMapTest {
    HashMap<Integer, String> hashMap = new HashMap<>();

    @Before
    public void setUp() {
        int i = 0;
        while (i < 12) {
            hashMap.insert(i, "test " + String.valueOf(i));
            i++;
        }
    }

    @Test
    public void checkInsertMethod() {
        assertTrue(hashMap.insert(12, "test 12"));
        assertTrue(hashMap.insert(13, "test 13"));
        assertTrue(hashMap.insert(14, "test 14"));
        assertFalse(hashMap.insert(1, "test false"));
        assertTrue(hashMap.insert(15, "test 15"));
    }

    @Test
    public void checkGetMethod() {
        assertThat("test 1", is(hashMap.get(1)));
        assertThat("test 2", is(hashMap.get(2)));
        assertThat("test 3", is(hashMap.get(3)));
        assertThat("test 4", is(hashMap.get(4)));
        assertThat("test 11", is(hashMap.get(11)));
        assertThat(null, is(hashMap.get(12)));
    }

    @Test
    public void checkDeleteMethod() {
        int size  = hashMap.getSize();
        int i = 0;
        while (i < 12) {
            assertTrue(hashMap.delete(i));
            assertThat(size - i - 1, is(hashMap.getSize()));
            i++;
        }
        assertThat(0, is(hashMap.getSize()));
    }

    @Test
    public void checkIteratorMethod() {
        assertTrue(hashMap.delete(5));
        int i = 1;
        Iterator<HashMap.Entry<Integer, String>> iterator = hashMap.iterator();
        while (i < hashMap.getSize()) {
            iterator.next();
            assertThat(iterator.hasNext(), is(true));
            i++;
        }
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
    }

    @Test(expected = NoSuchElementException.class)
    public void checkNoSuchElementsInIteratorMethodWhenCallNext12times() {
        int i = 1;
        assertTrue(hashMap.delete(5));
        Iterator<HashMap.Entry<Integer, String>> iterator = hashMap.iterator();
        while (i < hashMap.getSize()) {
            iterator.next();
            assertThat(iterator.hasNext(), is(true));
            i++;
        }
        iterator.next();
        assertThat(iterator.hasNext(), is(false));
        iterator.next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void checkConcurrentModificationInIteratorMethodWhenDeleteAfterCreatingIterator() {
        Iterator<HashMap.Entry<Integer, String>> iterator = hashMap.iterator();
        assertTrue(hashMap.delete(5));
        iterator.hasNext();
    }
}