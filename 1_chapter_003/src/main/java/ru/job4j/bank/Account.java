package ru.job4j.bank;


public class Account {

    double values;
    String reqs;

    public Account(double values, String requisites) {
        this.values = values;
        this.reqs = requisites;
    }

    public double getValues() {
        return this.values;
    }


    public String getReqs() {
        return this.reqs;
    }

    boolean transfer(Account destination, double amount) {
        boolean success = false;
        if (amount > 0 && amount < this.values && destination != null) {
            success = true;
            this.values -= amount;
            destination.values += amount;
        }
        return success;
    }

    public String toString() {
        return "Account{values=" + values + ", reqs='" + reqs + "}";
    }

    public boolean equals(Object o) {
        boolean result = false;
        Account account = (Account) o;
        if (this == o && this.reqs.equals(account.reqs)) {
            result = true;
        }
        return result;
    }

    public int hashCode() {
        return this.reqs.hashCode();
    }
}