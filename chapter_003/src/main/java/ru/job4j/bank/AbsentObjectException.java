package ru.job4j.bank;

public class AbsentObjectException extends RuntimeException {
    public AbsentObjectException(String msg) {
        super(msg);
    }
}
