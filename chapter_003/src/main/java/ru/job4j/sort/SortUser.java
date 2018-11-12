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
        list.sort(
                new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        int result = 0;
                        if ((o1.getName().length()) < (o2.getName().length())) {
                            result = -1;
                        } else if ((o1.getName().length()) > (o2.getName().length())) {
                            result = 1;
                        }
                        return result;
                    }
                }
        );
        return list;
    }

    public List<User> sortByAllFields(List<User> list) {
        list.sort(
                new Comparator<User>() {
                    @Override
                    public int compare(User o1, User o2) {
                        int result = o1.getName().compareTo(o2.getName());
                        if (result == 0) {
                            result = o1.getAge().compareTo(o2.getAge());
                        }
                        return result;
                    }
                }
        );
        return list;
    }


}
