package ru.job4j.models;

import java.util.List;
import java.util.Objects;

public class Order {
    private String fio;
    private String phone;
    private int hallNumber;
    private List<Integer> places;
    private int price;

    public Order(String fio, String phone, int hallNumber, List<Integer> places, int price) {
        this.fio = fio;
        this.phone = phone;
        this.hallNumber = hallNumber;
        this.places = places;
        this.price = price;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getHallNumber() {
        return hallNumber;
    }

    public void setHallNumber(int hallNumber) {
        this.hallNumber = hallNumber;
    }

    public List<Integer> getPlaces() {
        return places;
    }

    public void setPlaces(List<Integer> places) {
        this.places = places;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Order{"
                + "fio='" + fio + '\''
                + ", phone='" + phone + '\''
                + ", hallNumber='" + hallNumber + '\''
                + ", places=" + places + '\''
                + ", price=" + price
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Order order = (Order) o;
        return hallNumber == order.hallNumber
                && price == order.price
                && Objects.equals(fio, order.fio)
                && Objects.equals(phone, order.phone)
                && Objects.equals(places, order.places);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fio, phone, hallNumber, places, price);
    }
}
