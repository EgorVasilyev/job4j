package waitnotify;

import org.junit.Test;


public class SimpleBlockingQueueTest {
    @Test
    public void testOfQueue() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(5);
        Thread consumer = new Thread(() -> {
            int i = 1;
            while (true) {
                try {
                    queue.offer(i++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        consumer.setName("Consumer");

        Thread producer = new Thread(() -> {
            while (true) {
                try {
                    queue.poll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        producer.setName("Producer");

        consumer.start();
        producer.start();

        producer.join();
    }

}
