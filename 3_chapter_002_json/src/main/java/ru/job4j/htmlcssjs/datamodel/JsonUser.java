package ru.job4j.htmlcssjs.datamodel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class JsonUser {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("lastname")
    private String lastName;
    @JsonProperty("sex")
    private String sex;
    @JsonProperty("description")
    private String description;

    public JsonUser() {
    }

    public JsonUser(String name, String lastName, String sex, String password, String description) {
        this.name = name;
        this.lastName = lastName;
        this.sex = sex;
        this.description = description;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JsonUser user = (JsonUser) o;
        return id == user.id
                && Objects.equals(name, user.name)
                && Objects.equals(lastName, user.lastName)
                && Objects.equals(sex, user.sex)
                && Objects.equals(description, user.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, sex, description);
    }

    @Override
    public String toString() {
        return "User{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", lastName='" + lastName + '\''
                + ", sex='" + sex + '\''
                + '}';
    }
}
