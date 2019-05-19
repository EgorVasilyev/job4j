package ru.job4j.solid.lsp.food;

import java.util.Date;

public class Milk extends Food {
    public Milk(String name, Date createDate, Date expireDate, int price, int discount) {
        super(name, createDate, expireDate, price, discount);
    }
}
