package waitnotify;

import org.junit.Test;


public class SimpleBlockingQueueTest {
    @Test
    public void testOfQueue() throws InterruptedException {
        SimpleBlockingQueue<Integer> queue = new SimpleBlockingQueue<Integer>(5);
        Thread consumer = new Thread(() -> {
            int i = 1;
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    queue.offer(i++);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        });
        consumer.setName("Consumer");

        Thread producer = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
                try {
                    queue.poll();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    Thread.currentThread().interrupt();
                }
            }
        });
        producer.setName("Producer");

        consumer.start();
        producer.start();

        Thread.sleep(1000);
        consumer.interrupt();
        producer.interrupt();
    }

}
