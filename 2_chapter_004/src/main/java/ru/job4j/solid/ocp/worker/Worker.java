package ru.job4j.solid.ocp.worker;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Worker {
    private String name;
    private double rate;
    private int experience;

    public Worker() {
    }

    public Worker(final String name, double rate, int experience) {
        this.name = name;
        this.rate = rate;
        this.experience = experience;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }
}
