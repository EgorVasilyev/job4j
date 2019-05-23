package ru.job4j.solid.ocp.worker.converter;

import ru.job4j.solid.ocp.worker.Worker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ToTXT implements Converter {
    /**
     * Constructor ToTXT.
     */
    public ToTXT() {
    }
    /**
     * Method save. Генерация TXT из значений полей объектов в файл target
     * @param list лист объектов Worker
     */
    public void save(List<Worker> list, File target) throws IOException {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(target))) {
            list.forEach(worker -> {
                try {
                    bufferedWriter.write("worker:");
                    bufferedWriter.newLine();
                    bufferedWriter.write("   name: " + worker.getName());
                    bufferedWriter.newLine();
                    bufferedWriter.write("   experience: " + worker.getExperience());
                    bufferedWriter.newLine();
                    bufferedWriter.write("   rate: " + String.valueOf(worker.getRate()));
                    bufferedWriter.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
    }
    public void show(List<Worker> list) {
        list.forEach(worker -> {
            System.out.println("worker:");
            System.out.println("   name: " + worker.getName());
            System.out.println("   experience: " + worker.getExperience());
            System.out.println("   rate: " + String.valueOf(worker.getRate()));
        });
    }
    @Override
    public String toString() {
        return "TXT";
    }
}
