package nonblocking;

import org.junit.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
/**
 * Class NonBlockingTest - Неблокирующий кеш.
 */
public class NonBlockingTest {
    /**
     * Тест работы с очередью в нескольких потоках.
     */
    @Test
    public void testUpdateQueueInMultiThread() throws InterruptedException {
        AtomicReference<Exception> ex = new AtomicReference<>();
        NonBlocking queue = new NonBlocking();
        Thread t1 = new Thread(() -> {
            try {
                queue.add(new Base(0));
                queue.add(new Base(1));
                queue.add(new Base(2));
            } catch (OptimisticException e) {
                System.out.println("Thread1 interrupt");
                ex.set(e);
                Thread.currentThread().interrupt();
            }
        });
        t1.setName("Thread1");
        t1.start();
        Thread t2 = new Thread(() -> {
            try {
                queue.add(new Base(0));

            } catch (OptimisticException e) {
                System.out.println("Thread2 interrupt");
                ex.set(e);
                Thread.currentThread().interrupt();
            }
        });
        t2.setName("Thread2");
        t2.start();
        t1.join();
        t2.join();

        assertThat(queue.get(0).id, is(0));
        assertThat(queue.get(0).version, is(0));
        assertThat(queue.size(), is(3));

        Thread t3 = new Thread(() -> {
            try {
                queue.update(new Base(0, queue.get(0).version));
            } catch (OptimisticException e) {
                System.out.println("Thread3 is interrupted");
                System.out.println();
                ex.set(e);
                Thread.currentThread().interrupt();
            }
        });
        t3.setName("Thread3");

        Thread t4 = new Thread(() -> {
            try {
                //блок if для наглядности, что потоки запущены совместно
                if (t3.getState() == Thread.State.RUNNABLE) {
                    System.out.println("Threads 3 and 4 started together\n");
                    queue.update(new Base(0, queue.get(0).version));
                }
            } catch (OptimisticException e) {
                System.out.println("Thread4 is interrupted");
                System.out.println();
                ex.set(e);
                Thread.currentThread().interrupt();
            }
        });
        t4.setName("Thread4");

        t3.start();
        t4.start();

        t3.join();
        t4.join();

        //для наглядности
        Thread.sleep(500);
        System.out.println("finish model version is " + queue.get(0).version);
        System.out.println();

        assertThat(queue.size(), is(3));
        assertThat(queue.get(0).version, is(1));

        assertThat(ex.get().getMessage(), is("Data already updated by another thread!"));

        Thread.sleep(500);
        Thread t5 = new Thread(() -> {
            try {
                queue.delete(new Base(0, queue.get(0).version));
            } catch (OptimisticException e) {
                System.out.println("Thread5 interrupt");
                ex.set(e);
                Thread.currentThread().interrupt();
            }
        });
        t5.setName("Thread5");
        Thread t6 = new Thread(() -> {
            try {
                //блок if для наглядности, что потоки запущены совместно
                if (t5.getState() == Thread.State.RUNNABLE) {
                    System.out.println("Threads started together\n");
                    queue.delete(new Base(0, queue.get(0).version));
                }
            } catch (OptimisticException e) {
                System.out.println("Thread6 interrupt");
                ex.set(e);
                Thread.currentThread().interrupt();
            }
        });
        t6.setName("Thread6");

        t5.start();
        t6.start();

        t5.join();
        t6.join();

        Thread.sleep(500);
        assertThat(queue.size(), is(2));
    }
}