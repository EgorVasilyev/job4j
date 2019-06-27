package ru.job4j.servlets.datamodel;

import java.util.List;
import java.util.Objects;

public class Country {
    private int id;
    private String name;
    private List<City> cities;

    public Country(int id, String name, List<City> cities) {
        this.id = id;
        this.name = name;
        this.cities = cities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Country country = (Country) o;
        return id == country.id
                && Objects.equals(name, country.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "Country{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", cities=" + cities
                + '}';
    }
}
