package ru.job4j.list;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class UserConvertTest {

    @Test
    public void whenListConvertToHashMap() {
        UserConvert listConvert = new UserConvert();
        List<User> list = new ArrayList<>();
        User user1 = new User(8, "Egor", "Moscow");
        User user2 = new User(20, "Vika", "Balashikha");
        list.add(user1);
        list.add(user2);
        HashMap<Integer, User> resultHashMap = listConvert.process(list);
        String result1 = resultHashMap.get(8).getName();
        String result2 = resultHashMap.get(20).getCity();
        assertThat(result1, is("Egor"));
        assertThat(result2, is("Balashikha"));
    }
}
