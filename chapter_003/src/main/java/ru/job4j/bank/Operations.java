package ru.job4j.bank;

import java.util.*;


public class Operations {

    private TreeMap<User, ArrayList<Account>> clients = new TreeMap<User, ArrayList<Account>>();

    public void addUser(User user) {
        this.clients.putIfAbsent(user, new ArrayList<Account>());
    }

    public void deleteUser(User user) {
        this.clients.remove(user);
    }

    private Account getAccByInfo(String passport, String requisites) {
        ArrayList<Account> resultAccs = new ArrayList<>();
        for (Map.Entry<User, ArrayList<Account>> user : this.clients.entrySet()) {
            for (Account acc : this.clients.get(user.getKey())) {
                if (user.getKey().getPassport().equals(passport) && acc.reqs.equals(requisites)) {
                    resultAccs.add(acc);
                }
            }
        }
        return resultAccs.get(0);
    }

    public void addAccount(User user, Account account) {

        if (this.clients.containsKey(user) && !this.clients.get(user).contains(account)) {
            this.clients.get(user).add(account);
        }
    }

    private boolean actualAccByInfo(String passport, String requisites) {
        boolean result = false;
        for (Map.Entry<User, ArrayList<Account>> user : this.clients.entrySet()) {
            for (Account acc : this.clients.get(user.getKey())) {
                if (user.getKey().getPassport().equals(passport) && acc.reqs.equals(requisites)) {
                    result = true;
                }
            }
        }
        return result;
    }

        public void deleteAccount(User user, Account account) {
            if (this.clients.get(user).contains(account)) {
                this.clients.get(user).remove(account);
            }
        }

        public List<Account> getAccounts(User user) {
            List<Account> list = new ArrayList<>();
            if (this.clients.containsKey(user)) {
                list.addAll(this.clients.get(user));
            }
            return list;
        }
    public boolean transferMoney(String srcPassport, String srcRequisite, String destPassport, String dstRequisite, double amount) {
        return actualAccByInfo(srcPassport, srcRequisite) && actualAccByInfo(destPassport, dstRequisite)
                && getAccByInfo(srcPassport, srcRequisite).transfer(getAccByInfo(destPassport, dstRequisite), amount);
    }

        public String toString() {
            return "Bank{" + "accounts=" + clients + "}";
        }
    }