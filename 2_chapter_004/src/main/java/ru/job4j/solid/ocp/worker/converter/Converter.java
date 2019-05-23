package ru.job4j.solid.ocp.worker.converter;

import ru.job4j.solid.ocp.worker.Worker;

import java.io.File;
import java.util.List;

public interface Converter {
    void save(List<Worker> list, File target) throws Exception;
    void show(List<Worker> list) throws Exception;
}
