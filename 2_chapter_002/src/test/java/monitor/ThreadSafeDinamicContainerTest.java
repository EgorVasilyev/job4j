package monitor;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class ThreadSafeDinamicContainerTest {
    /**
     * Тест итератора с модификацией в одном потоке.
     */
    @Test
    public void testIteratorInOneThread() {
        ThreadSafeDinamicContainer<Integer> array = new ThreadSafeDinamicContainer<Integer>();
        array.add(1);
        array.add(2);
        array.add(3);
        array.add(4);
        Iterator<Integer> it = array.iterator();
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(1));
        array.add(5);
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(2));
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(3));
        array.add(6);
        assertThat(it.hasNext(), is(true));
        assertThat(it.next(), is(4));
        assertThat(it.hasNext(), is(false));
    }
    /**
     * Тест итератора с модификацией в нескольких потоках.
     */
    @Test
    public void testIteratorMultiThread() {
        ThreadSafeDinamicContainer<Integer> array = new ThreadSafeDinamicContainer<Integer>();
        array.add(1);
        array.add(2);
        array.add(3);
        Thread t1 = new Thread(() -> {
            Iterator<Integer> it = array.iterator();
            while (it.hasNext()) {
                Integer i = it.next();
                System.out.println("Thread1:" + i);
            }
        });
        t1.setName("Thread1");
        t1.start();

        Thread t2 = new Thread(() -> {
            System.out.println("Thread2");
            array.add(4);
            array.add(5);
            array.add(6);
        });
        t2.setName("Thread2");
        t2.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
