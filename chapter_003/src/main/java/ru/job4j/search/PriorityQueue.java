package ru.job4j.search;

import java.util.LinkedList;


public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<Task>();

    /**
     * Метод вставляет в нужную позицию элемент.
     * @param task задача
     */
    public void put(Task task) {
        int number = -1;
        for (Task taskValue : tasks) {
            if (taskValue.getPriority() > task.getPriority()) {
                number = tasks.indexOf(taskValue);
                break;
            }
        }
        if (number != -1) {
            tasks.add(number, task);
        } else {
            tasks.add(task);
        }
    }

    public Task take() {
        return this.tasks.poll();
    }
}