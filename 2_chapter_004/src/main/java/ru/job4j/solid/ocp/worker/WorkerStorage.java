package ru.job4j.solid.ocp.worker;

import java.util.ArrayList;
import java.util.List;

public class WorkerStorage {
    private String name;
    private List<Worker> workerList;

    public WorkerStorage() {
        this.workerList = new ArrayList<>();
    }
    public void add(Worker worker) {
        this.workerList.add(worker);
    }
    public String getName() {
        return name;
    }
    public List<Worker> getWorkerList() {
        return this.workerList;
    }
    public int size() {
        return this.workerList.size();
    }
}
