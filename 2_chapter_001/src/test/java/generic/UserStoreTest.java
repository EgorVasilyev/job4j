package generic;

import org.junit.Test;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserStoreTest {

    @Test
    public void whenAddTwoUsersAndFind() {
        UserStore userStore = new UserStore(4);
        User result = null;
        User user1 = new User("FirstUser");
        User user2 = new User("SecondUser");
        userStore.add(user1);
        userStore.add(user2);
        assertThat(userStore.findById("SecondUser"), is(user2));
        assertThat(userStore.findById("FirstUser"), is(user1));
        assertThat(userStore.findById("NoNameUser"), is(result));
    }

    @Test
    public void whenAddThreeUsersAndReplaceAndIterate() {
        UserStore userStore = new UserStore(4);
        Iterator<User> it = userStore.iter();
        User result = null;
        User user1 = new User("FirstUser");
        User user2 = new User("SecondUser");
        User user3 = new User("NewFirstUser");
        userStore.add(user1);
        userStore.add(user2);
        userStore.replace("FirstUser", user3);
        assertThat(userStore.findById("FirstUser"), is(result));
        assertThat(userStore.findById("NewFirstUser"), is(user3));
        assertThat(it.next().getId(), is("NewFirstUser"));
        assertThat(it.next().getId(), is("SecondUser"));
    }

    @Test
    public void whenAddThreeUsersAndDeleteSecondUser() {
        UserStore userStore = new UserStore(4);
        Iterator<User> it = userStore.iter();
        User result = null;
        User user1 = new User("FirstUser");
        User user2 = new User("SecondUser");
        User user3 = new User("ThirdUser");
        userStore.add(user1);
        userStore.add(user2);
        userStore.add(user3);
        userStore.delete("SecondUser");
        assertThat(it.next().getId(), is("FirstUser"));
        assertThat(it.next().getId(), is("ThirdUser"));
    }

    @Test (expected = NoSuchElementException.class)
    public void whenAddOneUserOnly() {
        UserStore userStore = new UserStore(4);
        Iterator<User> it = userStore.iter();
        User user1 = new User("FirstUser");
        userStore.add(user1);
        it.next();
        it.next();
    }
}
