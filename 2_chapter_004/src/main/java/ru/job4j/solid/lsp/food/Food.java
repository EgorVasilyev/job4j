package ru.job4j.solid.lsp.food;

import java.util.Date;

public class Food {
    private String name;
    private final Date createDate;
    private final Date expireDate;
    private int price;
    private int discount;

    public Food(String name, Date createDate, Date expireDate, int price, int discount) {
        this.name = name;
        this.createDate = createDate;
        this.expireDate = expireDate;
        if (discount < 0) {
            this.discount = 0;
        } else if (discount > 100) {
            this.discount = 100;
        } else {
            this.discount = discount;
        }
        this.price = price * (100 - this.discount) / 100;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price * (100 - this.discount) / 100;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        if (discount < 0) {
            this.discount = 0;
        } else if (discount > 100) {
            this.discount = 100;
        } else {
            this.discount = discount;
        }
        this.price = this.price * (100 - this.discount) / 100;
    }

    @Override
    public String toString() {
        return "Food{"
                + "name='" + name + '\''
                + ", price=" + price
                + ", discount=" + discount
                + '}';
    }
}
