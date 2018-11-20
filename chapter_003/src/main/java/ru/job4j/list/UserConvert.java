package ru.job4j.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class UserConvert {

    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> result = new HashMap<Integer, User>();
        list.forEach(x -> result.put(x.getId(), x));
        return result;
    }
}
