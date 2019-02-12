package monitor;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserStorageTest {
    /**
     * Тест трансфера в одном потоке
     */
    @Test
    public void whenTransferThousandThenItIsTransfered() {
        UserStorage store = new UserStorage();
        User user = new User(1, 1000);
        User user2 = new User(2, 2000);
        store.add(user);
        store.add(user2);
        store.transfer(1, 2, 1000);
        assertThat(user.getAmount(), is(0));
        assertThat(user2.getAmount(), is(3000));
    }
    /**
     * Тест трансфера в нескольких потоках
     */
    @Test
    public void whenTransferHundredsInMultiThreadThenItIsTransferedTrue() {
        UserStorage store = new UserStorage();
        User user = new User(1, 1000);
        User user2 = new User(2, 2000);
        store.add(user);
        store.add(user2);
        System.out.println("Amount of user1:                         " + user.getAmount());
        Thread t1 = new Thread(() -> {
            int i = 15;
            while (i != 0) {
                store.transfer(1, 2, 20);
                System.out.println("Thread 1 (-20 ruble). Amount of user1:   " + user.getAmount());
                i--;
            }
        });
        t1.setName("Thread1");
        t1.start();
        Thread t2 = new Thread(() -> {
            int i = 10;
            while (i != 0) {
                store.transfer(1, 2, 50);
                System.out.println("Thread 2 (-50 ruble). Amount of user1:   " + user.getAmount());
                i--;
            }
        });
        t2.setName("Thread2");
        t2.start();
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        assertThat(user.getAmount(), is(200));
        assertThat(user2.getAmount(), is(2800));
    }
}
