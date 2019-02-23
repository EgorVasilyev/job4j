package pool;

import waitnotify.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Class ThreadPool - Пул потоков.
 */
public class ThreadPool {
    private final List<Thread> threads = new LinkedList<>();
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<>(100);
    private final Object threadLock = new Object();

    public ThreadPool() {
        int size = Runtime.getRuntime().availableProcessors();
        for (int i = 0; i < size; i++) {
            threads.add(new Task(i));
        }
    }
    /**
     * Метод work. Добавление задания.
     * @param job Задание.
     */
    public void work(Runnable job) {
        synchronized (threadLock) {
            try {
                this.tasks.offer(job);
                threadLock.notify();
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
    /**
     * Метод shutdown. Остановка пула.
     */
    public void shutdown() {
        this.threads.stream().
                peek(Thread::interrupt).
                forEach(task -> System.out.println(task.getName() + " is interrupted"));
    }
    /**
     * Метод size. Получение числа заданий.
     * @return Размер.
     */
    public int size() {
        return this.tasks.size();
    }

    public List<Thread> getThreads() {
        return this.threads;
    }

    /**
     * Class Task - Пул потоков.
     */
    private class Task extends Thread {
        public Task(int i) {
            setName("Thread" + i);
            System.out.println(getName() + " is " + getState());
            start();
        }

        @Override
        public void run() {
            while (!Thread.currentThread().isInterrupted()) {
                synchronized (threadLock) {
                    try {
                        if (tasks.isEmpty()) {
                            threadLock.wait();
                        }
                        tasks.poll().run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
                }
            }
        }
    }
}