package ru.job4j.solid.lsp.food;

import java.util.Date;

public class Meet extends Food {
    public Meet(String name, Date createDate, Date expireDate, int price, int discount) {
        super(name, createDate, expireDate, price, discount);
    }
}
