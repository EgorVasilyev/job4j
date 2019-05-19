package ru.job4j.solid.lsp.food;

import java.util.Date;

public class Cheese extends Food {
    public Cheese(String name, Date createDate, Date expireDate, int price, int discount) {
        super(name, createDate, expireDate, price, discount);
    }
}
