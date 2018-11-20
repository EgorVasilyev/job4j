package ru.job4j.search;

import java.util.LinkedList;


public class PriorityQueue {
    private LinkedList<Task> tasks = new LinkedList<Task>();

    /**
     * Метод вставляет в нужную позицию элемент.
     * @param task задача
     */
    public void put(Task task) {
        if (tasks.stream()
                .anyMatch(taskIn -> taskIn.getPriority() > task.getPriority())) {
            tasks.stream()
                    .filter(taskIn -> taskIn.getPriority() > task.getPriority())
                    .findFirst()
                    .ifPresent(
                            taskIn -> tasks.add(tasks.indexOf(taskIn), task));
        } else {
            tasks.add(task);
        }

    }

    public Task take() {
        return this.tasks.poll();
    }
}