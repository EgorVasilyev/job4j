package ru.job4j.bank;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OperationsTest {
    @Test
    public void whenAddUserAndTwoAccounts() {
        Operations act = new Operations();
        User user1 = new User(123, "Sergey");
        Account acc1 = new Account(60000, "StalProject");
        Account acc2 = new Account(0, "IP ishu rabotu");
        act.addUser(user1);
        act.addAccount(user1, acc1);
        act.addAccount(user1, acc2);
        double resultValue = act.getAccounts(user1).get(0).values;
        String resultReq = act.getAccounts(user1).get(1).reqs;
        assertThat(resultValue, is(60000.0));
        assertThat(resultReq, is("IP ishu rabotu"));
    }
    @Test
    public void whenAddSameAccounts() throws ObjectAlreadyContainedException {
        Operations act = new Operations();
        User user1 = new User(123, "Sergey");
        Account acc1 = new Account(60000, "StalProject");
        act.addUser(user1);
        act.addAccount(user1, acc1);
        String result = null;
        try {
            act.addAccount(user1, acc1);
        } catch (ObjectAlreadyContainedException oace) {
            result = "This acc has already been added";
        }
        assertThat(result, is("This acc has already been added"));
    }

    @Test
    public void whenDelUser() throws AbsentObjectException {
        Operations act = new Operations();
        User user1 = new User(123, "Sergey");
        Account acc1 = new Account(60000, "StalProject");
        act.addUser(user1);
        act.addAccount(user1, acc1);
        String result = null;
        assertThat(act.getAccounts(user1) + "", is("[Account{values=60000.0, reqs='StalProject}]"));
        act.deleteUser(user1);
        try {
            act.deleteUser(user1);
        } catch (AbsentObjectException aoe) {
            result = "This user was not creat or deleted";
        }
        assertThat(result, is("This user was not creat or deleted"));
    }
    @Test
    public void whenDelAccount() throws AbsentObjectException {
        Operations act = new Operations();
        User user1 = new User(123, "Sergey");
        Account acc1 = new Account(60000, "StalProject");
        Account acc2 = new Account(0, "IP ishu rabotu");
        act.addUser(user1);
        act.addAccount(user1, acc1);
        act.addAccount(user1, acc2);
        String result = null;
        act.deleteAccount(user1, acc1);
        try {
            act.deleteAccount(user1, acc1);
        } catch (AbsentObjectException aoe) {
            result = "This acc was deleted";
        }
        assertThat(result, is("This acc was deleted"));
        assertThat(act.getAccounts(user1) + "", is("[Account{values=0.0, reqs='IP ishu rabotu}]"));

    }

    @Test
    public void whenTransferTrue() {
        Operations act = new Operations();
        User user1 = new User(123, "Sergey");
        Account acc1 = new Account(60000, "StalProject");
        User user2 = new User(345, "Vasya");
        Account acc2 = new Account(0, "IP ishu rabotu");
        act.addUser(user1);
        act.addUser(user2);
        act.addAccount(user1, acc1);
        act.addAccount(user2, acc2);
        boolean result = act.transfer(user1, acc1, user2, acc2, 10000);
        assertThat(result, is(true));
    }

    @Test
    public void whenTransferFalse() {
        Operations act = new Operations();
        User user1 = new User(123, "Sergey");
        Account acc1 = new Account(60000, "StalProject");
        User user2 = new User(345, "Vasya");
        Account acc2 = new Account(0, "IP ishu rabotu");
        act.addUser(user1);
        act.addUser(user2);
        act.addAccount(user1, acc1);
        act.addAccount(user2, acc2);
        boolean result = act.transfer(user2, acc2, user1, acc1, 10000);
        assertThat(result, is(false));
    }
}