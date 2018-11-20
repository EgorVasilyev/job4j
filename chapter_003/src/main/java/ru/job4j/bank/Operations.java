package ru.job4j.bank;

import java.util.*;
import java.util.stream.Collectors;


public class Operations {

    private TreeMap<User, ArrayList<Account>> clients = new TreeMap<User, ArrayList<Account>>();

    public void addUser(User user) {
        if (this.clients.entrySet().stream()
                .noneMatch(userIn -> userIn.getKey().equals(user))) {
            this.clients.put(user, new ArrayList<Account>());
        }
    }

    public void deleteUser(User user) {
        if (this.clients.entrySet().stream()
                .anyMatch(userIn -> userIn.getKey().equals(user))) {
            this.clients.remove(user);
        }
    }

    public void addAccount(User user, Account account) {
        if (this.clients.entrySet().stream()
                .filter(userIn -> userIn.getKey().equals(user))
                .noneMatch(userIn -> userIn.getValue().contains(account))) {
            this.clients.get(user).add(account);
        }
    }

    private Account getAccByInfo(String passport, String requisites) {
        Account result = null;
        if (actualAccByInfo(passport, requisites)) {
            ArrayList<Account> foundAccsByPassport = new ArrayList<>();
            this.clients.entrySet().stream()
                    .filter((user -> user.getKey().getPassport().equals(passport)))
                    .forEach(user -> foundAccsByPassport.addAll(user.getValue()));
            ArrayList<Account> resultAcc = new ArrayList<>();
            foundAccsByPassport.stream()
                    .filter(acc -> acc.reqs.equals(requisites))
                    .forEach(resultAcc::add);
            result = resultAcc.get(0);
        }
        return result;
    }

    private boolean actualAccByInfo(String passport, String requisites) {
        boolean result = false;
        boolean userContains = this.clients.entrySet().stream()
                .anyMatch(user -> user.getKey().getPassport().equals(passport));
        boolean accountContains = false;
        if (userContains) {
            ArrayList<Account> foundAccsByPassport = new ArrayList<>();
            this.clients.entrySet().stream()
                    .filter((user -> user.getKey().getPassport().equals(passport)))
                    .forEach(user -> foundAccsByPassport.addAll(user.getValue()));
            accountContains = foundAccsByPassport.stream()
                    .anyMatch(acc -> acc.reqs.equals(requisites));
        }
        if (userContains && accountContains) {
            result = true;
        }
        return result;
    }

    public void deleteAccount(User user, Account account) {
        if (this.clients.entrySet().stream()
                .filter((userIn -> userIn.getKey().equals(user)))
                .anyMatch(userIn -> userIn.getValue().contains(account))) {
            this.clients.get(user).remove(account);
        }
    }

    public List<Account> getAccounts(User user) {
        List<Account> list = new ArrayList<>();
        this.clients.entrySet().stream()
                .filter(userIn -> userIn.getKey().equals(user))
                .forEach(userIn -> list.addAll(userIn.getValue()));
        return list;
    }

    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount) {
        return getAccByInfo(srcPassport, srcRequisite)
                .transfer(getAccByInfo(destPassport, dstRequisite), amount);
    }

    public String toString() {
        return "Bank{" + "accounts=" + clients + "}";
    }
}