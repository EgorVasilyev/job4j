package ru.job4j.sort;

import org.junit.Test;
import ru.job4j.sort.User;

import java.util.*;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SortUserTest {

    @Test
    public void whenListConvertToTreeSet() {
        SortUser sortUser = new SortUser();
        List<User> list = new ArrayList<>();
        User user1 = new User(25, "Egor");
        User user2 = new User(2, "Leva");
        User user3 = new User(21, "Vika");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        Set<User> resultUsers = sortUser.sort(list);
        String result1 = resultUsers.toString();
        assertThat(result1, is("[Leva 2, Vika 21, Egor 25]"));
    }
}
