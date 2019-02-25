package pool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class EmailNotification - Отправка почты.
 */
public class EmailNotification {
    ExecutorService pool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    /**
     * Метод emailTo. Постановка задания на отправку емейл.
     * @param user Пользователь.
     */
    public void emailTo(User user) {
        pool.submit(() -> {
                String subject = String.format("Notification %s to email: %s", user.getUserName(), user.getEmail());
                String body = String.format("Add a new event to: %s", user.getUserName());
                System.out.println(Thread.currentThread().getName());
                send(subject, body, user.getEmail());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }
    /**
     * Метод send. Отправка емейл.
     * @param subject Тема.
     * @param body Тело письма.
     * @param email Адрес.
     */
    private void send(String subject, String body, String email) {
        System.out.format("%s.\n%s\n\n", subject, body);
    }
    /**
     * Метод close. Остановка пула.
     */
    public void close() {
        pool.shutdown();
        try {
            Thread.sleep(1000);
            if (pool.isShutdown()) {
                System.out.println("pool is SHUTDOWN");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
