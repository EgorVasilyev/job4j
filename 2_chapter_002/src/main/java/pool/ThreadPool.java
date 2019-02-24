package pool;

import waitnotify.SimpleBlockingQueue;

import java.util.LinkedList;
import java.util.List;

/**
 * Class ThreadPool - Пул потоков.
 */
public class ThreadPool {
    //список потоков
    private final List<Task> threads = new LinkedList<Task>();
    //очередь заданий
    private final SimpleBlockingQueue<Runnable> tasks = new SimpleBlockingQueue<Runnable>(100);

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
            try {
                this.tasks.offer(job);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
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
    /**
     * Метод getThreads. Получение списка потоков.
     * @return Список потоков.
     */
    public List<Task> getThreads() {
        return this.threads;
    }

    /**
     * Class Task - Получение задания.
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
                    try {
                        tasks.poll().run();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                        Thread.currentThread().interrupt();
                    }
            }
        }
    }
}