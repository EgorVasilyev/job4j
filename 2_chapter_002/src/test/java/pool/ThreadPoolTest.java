package pool;

import org.junit.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
/**
 * Class ThreadPoolTest - Пул потоков. Автотесты.
 */
public class ThreadPoolTest {
    private static class Job implements Runnable {
        private int a;
        private int i;

        public Job(int i) {
            this.i = i;
        }

        @Override
        public void run() {
            for (int i = 1; i <= 10; i++) {
                this.a = (int) (Math.random() * 100);
                System.out.println("useful work №" + i + " {a=" + this.a + "} in Job " + this.i);
            }
        }
        @Override
        public String toString() {
            return "Job " + i + " in " + Thread.currentThread().getName();
        }
    }
    /**
     * Тест работы с пулом потоков.
     */
    @Test
    public void testThreadPool() throws InterruptedException {
        ThreadPool threadPool = new ThreadPool();

        System.out.println();
        for (Thread t: threadPool.getThreads()) {
            System.out.println(t.getName() + " before WORK is " + t.getState());
        }
        System.out.println();

        assertThat(threadPool.size(), is(0));
        for (int i = 0; i < 10; i++) {
            threadPool.work(new Job(i));
        }

        Thread.sleep(1000);
        assertThat(threadPool.size(), is(0));

        System.out.println();
        for (Thread t: threadPool.getThreads()) {
            System.out.println(t.getName() + " after WORK and before SHUTDOWN is " + t.getState());
        }
        System.out.println();

        for (int i = 0; i < 10; i++) {
            threadPool.work(new Job((i+1)*10));
        }

        threadPool.shutdown();
        Thread.sleep(1000);

        System.out.println();
        for (Thread t: threadPool.getThreads()) {
            System.out.println(t.getName() + " after SHUTDOWN is " + t.getState());
        }
    }
}
