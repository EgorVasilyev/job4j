package ru.job4j.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;


public class Operations {

    private TreeMap<User, ArrayList<Account>> clients = new TreeMap<User, ArrayList<Account>>();

    public void addUser(User user) {
        if (!this.clients.containsKey(user)) {
            this.clients.put(user, new ArrayList<Account>());
        } else {
            throw new ObjectAlreadyContainedException("asd");
        }
    }

    public void deleteUser(User user) {
        if (this.clients.containsKey(user)) {
            this.clients.remove(user);
        } else {
            throw new AbsentObjectException("asd");
        }
    }

    public void addAccount(User user, Account account) {
        if (this.clients.containsKey(user) && !this.clients.get(user).contains(account)) {
            this.clients.get(user).add(account);
        } else {
            throw new ObjectAlreadyContainedException("asd");
        }
    }

    private Account getActualAccount(User user, Account account) {
        if (this.clients.containsKey(user) && this.clients.get(user).contains(account)) {
            ArrayList<Account> list = this.clients.get(user);
            return list.get(list.indexOf(account));
        } else {
            throw new AbsentObjectException("asd");
        }
    }

    public void deleteAccount(User user, Account account) {
        if (this.clients.containsKey(user) && this.clients.get(user).contains(account)) {
            this.clients.get(user).remove(account);
        } else {
            throw new AbsentObjectException("asd");
        }
    }

    public List<Account> getAccounts(User user) {
        if (this.clients.containsKey(user)) {
            return this.clients.get(user);
        } else {
            throw new AbsentObjectException("asd");
        }
    }

    public boolean transfer(User user1, Account account1,
                            User user2, Account account2, double amount) {
        return this.clients.containsKey(user1) && this.clients.get(user1).contains(account1)
                && this.clients.containsKey(user2) && this.clients.get(user2).contains(account2)
                && getActualAccount(user1, account1).transfer(
                getActualAccount(user2, account2), amount);
    }
    public String toString() {
        return "Bank{" + "accounts=" + clients + "}";
    }
}