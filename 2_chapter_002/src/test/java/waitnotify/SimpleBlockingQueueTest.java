package waitnotify;

import org.junit.Test;


public class SimpleBlockingQueueTest {
    @Test
    public void testOfQueue() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(5);
        Thread consumer = new Thread(() -> {
            int i = 1;
            while (true) {
                queue.offer(i++);
            }
        });
        consumer.setName("Consumer");

        Thread producer = new Thread(() -> {
            while (true) {
                queue.poll();
            }
        });
        producer.setName("Producer");

        consumer.start();
        producer.start();

        producer.join();
    }

}
