package ru.job4j.models;

import java.util.List;
import java.util.Objects;

public class Hall {
    private int id;
    private int number;
    private int countRanges;
    private int countPlaces;
    private List<Integer> blockedPlaces;

    public Hall(int number, int countRanges, int countPlaces, List<Integer> blockedPlaces) {
        this.number = number;
        this.countRanges = countRanges;
        this.countPlaces = countPlaces;
        this.blockedPlaces = blockedPlaces;
    }

    public Hall() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getCountRanges() {
        return countRanges;
    }

    public void setCountRanges(int countRanges) {
        this.countRanges = countRanges;
    }

    public int getCountPlaces() {
        return countPlaces;
    }

    public void setCountPlaces(int countPlaces) {
        this.countPlaces = countPlaces;
    }

    public List<Integer> getBlockedPlaces() {
        return blockedPlaces;
    }

    public void setBlockedPlaces(List<Integer> blockedPlaces) {
        this.blockedPlaces = blockedPlaces;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Hall hall = (Hall) o;
        return id == hall.id
                && number == hall.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, number);
    }

    @Override
    public String toString() {
        return "Hall{"
                + "number=" + number
                + ", countRanges=" + countRanges
                + ", countPlaces=" + countPlaces
                + ", blockedPlaces=" + blockedPlaces
                + '}';
    }
}
