package pool;

import org.junit.Test;

public class EmailNotificationTest {
    @Test
    public void test() {
        EmailNotification en = new EmailNotification();
        for (int i = 1; i <= 10; i++) {
            en.emailTo(new User(("NAME_" + i), ("MAIL_" + i + "@email.com")));
        }
        try {
            Thread.currentThread().join(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        en.close();
    }
}
