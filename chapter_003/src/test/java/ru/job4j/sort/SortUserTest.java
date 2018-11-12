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

    @Test
    public void whenListSortByLengthOfName() {
        SortUser sortUser = new SortUser();
        List<User> list = new ArrayList<>();
        User user1 = new User(25, "Egor");
        User user2 = new User(2, "Lev");
        User user3 = new User(21, "Viktoria");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        List<User> resultUsers = sortUser.sortNameLength(list);
        String result1 = resultUsers.toString();
        assertThat(result1, is("[Lev 2, Egor 25, Viktoria 21]"));
    }

    @Test
    public void whenListSortByAllFields() {
        SortUser sortUser = new SortUser();
        List<User> list = new ArrayList<>();
        User user3 = new User(25, "Egor");
        User user2 = new User(2, "Egor");
        User user1 = new User(21, "Viktoria");
        User user4 = new User(45, "Misha");
        list.add(user1);
        list.add(user2);
        list.add(user3);
        list.add(user4);
        List<User> resultUsers = sortUser.sortByAllFields(list);
        String result1 = resultUsers.toString();
        assertThat(result1, is("[Egor 2, Egor 25, Misha 45, Viktoria 21]"));
    }
}
