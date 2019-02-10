package monitor;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

public class UserStorageTest {

    @Test
    public void whenTransferTenThenItIsTransfered() {
        UserStorage store = new UserStorage();
        User user = new User(1, 1000);
        User user2 = new User(2, 2000);
        store.add(user);
        store.add(user2);
        store.transfer(1, 2, 1000);
        assertThat(user.getAmount(), is(0));
        assertThat(user2.getAmount(), is(3000));
    }

}
