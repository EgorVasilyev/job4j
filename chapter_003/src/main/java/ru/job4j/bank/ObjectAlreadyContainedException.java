package ru.job4j.bank;

public class ObjectAlreadyContainedException extends RuntimeException {
    public ObjectAlreadyContainedException(String msg) {
        super(msg);
    }
}
