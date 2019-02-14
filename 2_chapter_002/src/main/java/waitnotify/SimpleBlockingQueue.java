package waitnotify;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.LinkedList;
import java.util.Queue;

@ThreadSafe
public class SimpleBlockingQueue<T> {

    @GuardedBy("this")
    private Queue<T> queue = new LinkedList<>();
    private int capacity = 3;

    public SimpleBlockingQueue() {
    }

    public SimpleBlockingQueue(int capacity) {
        if (capacity > 3) {
            this.capacity = capacity;
        }
    }

    public synchronized void offer(T value) {
        while (this.isFull()) {
            try {
                System.out.println("queue is FULL, waiting for poll");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        queue.offer(value);
        System.out.println("offer = " + value);
        notify();
    }

    public synchronized T poll() {
        while (queue.isEmpty()) {
            try {
                System.out.println("queue is EMPTY, waiting for offer");
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        T result = queue.poll();
        notify();
        System.out.println("poll = " + result);
        return result;
    }

    private synchronized boolean isFull() {
        return queue.size() == capacity;
    }
}