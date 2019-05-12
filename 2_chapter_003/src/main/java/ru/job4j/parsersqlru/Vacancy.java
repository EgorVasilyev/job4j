package ru.job4j.parsersqlru;

import java.util.Date;
import java.util.Objects;

public class Vacancy {
    private String name;
    private String link;
    private Date created;
    private String text;

    public Vacancy() { }

    public Vacancy(String name, String link, Date created, String text) {
        this.name = name;
        this.link = link;
        this.created = created;
        this.text = text;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "Vacancy{"
                + "\nNAME =    " + name
                + "\nLINK =    " + link
                + "\nDATE =    " + created
                + "\nTEXT =    \n" + text
                + "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Vacancy vacancy = (Vacancy) o;
        return Objects.equals(name, vacancy.name)
                && Objects.equals(created, vacancy.created);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, created);
    }
}
