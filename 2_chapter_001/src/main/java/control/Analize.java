package control;

import java.util.HashMap;
import java.util.List;

public class Analize {
    Info info = new Info();
    HashMap<Integer, User> checkUser = new HashMap<Integer, User>();

    public Info diff(List<User> previous, List<User> current) {
        current.forEach(user -> checkUser.put(user.id, user));
        for (User user : previous) {
            User result = checkUser.remove(user.id);
            if (result == null) {
                info.deleted++;
            } else if (!result.name.equals(user.name)) {
                info.changed++;
            }
        }
        info.added = checkUser.size();
        checkUser.clear();
        return info;
    }

    public static class User {
        int id;
        String name;
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
    }

    public static class Info {
        int added;
        int changed;
        int deleted;
        @Override
        public String toString() {
            return "added=" + added + ", changed=" + changed + ", deleted=" + deleted;
        }
    }
}
