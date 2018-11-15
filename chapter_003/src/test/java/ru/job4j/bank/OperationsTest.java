package ru.job4j.bank;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class OperationsTest {

    @Test
    public void whenAddUserAndTwoAccounts() {
        Operations act = new Operations();
        User user1 = new User("123", "Sergey");
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
    public void whenAddSameAccounts() {
        Operations act = new Operations();
        User user1 = new User("123", "Sergey");
        Account acc1 = new Account(60000, "StalProject");
        act.addUser(user1);
        act.addAccount(user1, acc1);
        act.addAccount(user1, acc1);

        assertThat(act.getAccounts(user1) + "", is("[Account{values=60000.0, reqs='StalProject}]"));
    }

    @Test
    public void whenDelUser()  {
        Operations act = new Operations();
        User user1 = new User("123", "Sergey");
        Account acc1 = new Account(60000, "StalProject");
        act.addUser(user1);
        act.addAccount(user1, acc1);
        assertThat(act.getAccounts(user1) + "", is("[Account{values=60000.0, reqs='StalProject}]"));
        act.deleteUser(user1);
        assertThat(act.getAccounts(user1) + "", is("[]"));
    }
    @Test
    public void whenDelAccount() {
        Operations act = new Operations();
        User user1 = new User("123", "Sergey");
        Account acc1 = new Account(60000, "StalProject");
        Account acc2 = new Account(0, "IP ishu rabotu");
        act.addUser(user1);
        act.addAccount(user1, acc1);
        act.addAccount(user1, acc2);
        act.deleteAccount(user1, acc1);
        assertThat(act.getAccounts(user1) + "", is("[Account{values=0.0, reqs='IP ishu rabotu}]"));
    }

    @Test
    public void whenTransferTrue() {
        Operations act = new Operations();
        User user1 = new User("123", "Sergey");
        Account acc1 = new Account(60000, "StalProject");
        User user2 = new User("321", "Vasya");
        Account acc2 = new Account(0, "IP ishu rabotu");
        act.addUser(user1);
        act.addUser(user2);
        act.addAccount(user1, acc1);
        act.addAccount(user2, acc2);
        boolean result = act.transferMoney("123", "StalProject",
                "321", "IP ishu rabotu",
                10000);
        assertThat(result, is(true));
    }

    @Test
    public void whenTransferFalse() {
        Operations act = new Operations();
        User user1 = new User("123", "Sergey");
        Account acc1 = new Account(60000, "StalProject");
        User user2 = new User("321", "Vasya");
        Account acc2 = new Account(0, "IP ishu rabotu");
        act.addUser(user1);
        act.addUser(user2);
        act.addAccount(user1, acc1);
        act.addAccount(user2, acc2);
        boolean result = act.transferMoney("321", "IP ishu rabotu",
                "123", "StalProject",
                10000);
        assertThat(result, is(false));
    }
}