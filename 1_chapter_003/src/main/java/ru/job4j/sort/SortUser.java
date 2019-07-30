package ru.job4j.sort;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SortUser {

    public Set<User> sort(List<User> list) {
        return new TreeSet<User>(list);
    }

    public List<User> sortNameLength(List<User> list) {
        list.sort(Comparator.comparing(user -> user.getName().length()));
        return list;
    }

    public List<User> sortByAllFields(List<User> list) {
        list.sort(Comparator.comparing(User::getName).thenComparing(User::getAge));
        return list;
    }

}
