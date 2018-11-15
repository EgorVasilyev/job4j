package ru.job4j.bank;



public class User implements Comparable<User> {
    private String passport;
    private String name;

    public User(String passport, String name) {
        this.passport = passport;
        this.name = name;
    }

    public String getPassport() {
        return passport;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public int compareTo(User o) {
        return this.passport.compareTo(o.passport);
    }
}