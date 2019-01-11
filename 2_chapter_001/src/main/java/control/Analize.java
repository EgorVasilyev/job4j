package control;

import java.util.List;

public class Analize {
    Info info = new Info();
    public Info diff(List<User> previous, List<User> current) {
        for (User curUser : current) {
            boolean addResult = false;
            for (User prevUser : previous) {
                if (curUser.id == prevUser.id) {
                    addResult = true;
                    break;
                }
            }
            if (!addResult) {
                info.added++;
            }
        }

        for (User prevUser : previous) {
            for (User curUser : current) {
                if (prevUser.id == curUser.id && curUser.changedName) {
                    info.changed++;
                }
            }
        }

        for (User prevUser : previous) {
            boolean delResult = false;
            for (User curUser : current) {
                if (prevUser.id == curUser.id) {
                    delResult = true;
                    break;
                }
            }
            if (!delResult) {
                info.deleted++;
            }
        }
        return info;
    }

    public static class User {
        int id;
        String name;
        boolean changedName;
        public User(int id, String name) {
            this.id = id;
            this.name = name;
        }
        public void setName(String newName) {
            this.name = newName;
            changedName = true;
        }
        public void refreshStatusName() {
            changedName = false;
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
