package ru.job4j.solid.ocp.worker;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class Workers {
    private List<Worker> workers;

    public Workers() {
    }

    public Workers(List<Worker> workers) {
        this.workers = workers;
    }

    public List<Worker> getWorker() {
        return workers;
    }

    public void setWorker(List<Worker> workers) {
        this.workers = workers;
    }
}
