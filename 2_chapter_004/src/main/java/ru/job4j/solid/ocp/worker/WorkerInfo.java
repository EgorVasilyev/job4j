package ru.job4j.solid.ocp.worker;

import ru.job4j.solid.ocp.worker.converter.Converter;
import ru.job4j.solid.ocp.worker.converter.ToJSON;
import ru.job4j.solid.ocp.worker.converter.ToTXT;
import ru.job4j.solid.ocp.worker.converter.ToXML;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class WorkerInfo {
    private WorkerStorage workerStorage;
    private HashMap<String, Converter> converters;
    private HashMap<String, String> filterKeys;

    public WorkerInfo(WorkerStorage workerStorage) {
        this.workerStorage = workerStorage;
        this.converters = new HashMap<>();
        this.filterKeys = new HashMap<>();
        this.filterKeys.put("0", "without filter");
        this.filterKeys.put("1", "name");
        this.filterKeys.put("2", "rate");
        this.filterKeys.put("3", "experience");
    }

    public Converter addConverter(String key, Converter converter) {
        return this.converters.put(key, converter);
    }

    public void startShow() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String converterKey;
        do {
            this.showConverterKeys();
            converterKey = scanner.nextLine();
            if (!"exit".equals(converterKey)) {
                if (this.converters.containsKey(converterKey)) {
                    String filterKey;
                    boolean result;
                    do {
                        this.showFilterKeys();
                        filterKey = scanner.nextLine();
                        result = this.filterKeys.containsKey(filterKey);
                        if (!"exit".equals(filterKey)) {
                            if (result) {
                                this.converters.get(converterKey).show(this.getFilteredWorkerList(filterKey));
                            } else {
                                System.out.println("Введенный ключ некорректен. Используйте ключи из списка.");
                            }
                        } else {
                            converterKey = "exit";
                        }
                    } while (!"exit".equals(filterKey) && !result);
                } else {
                    System.out.println("Введенный ключ некорректен. Используйте ключи из списка.");
                }
            }
        } while (!"exit".equals(converterKey));
    }

    private List<Worker> getFilteredWorkerList(String filterKey) {
        List<Worker> result = new ArrayList<>();
        int key = Integer.parseInt(filterKey);
        if (key == 0) {
            result = this.workerStorage.getWorkerList();
        } else {
            if (key == 1) {
                result = this.getFilteredByNameWorkerList();
            }
            if (key == 2) {
                result = this.getFilteredByNumberWorkerList("rate");
            }
            if (key == 3) {
                result = this.getFilteredByNumberWorkerList("experience");
            }
        }
        return result;
    }

    private List<Worker> getFilteredByNameWorkerList() {
        List<Worker> result = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        Pattern pattern;
        System.out.println("Введите полное имя работника или маску имени:");
        String nameMask = scanner.nextLine();
        if (nameMask.contains("*")) {
            StringBuilder regexBuilder = new StringBuilder("^");
            for (int i = 0; i < nameMask.length(); i++) {
                if (nameMask.charAt(i) == '*') {
                    regexBuilder.append(".*");
                } else {
                    regexBuilder.append(nameMask.charAt(i));
                }
            }
            regexBuilder.append("$");
            pattern = Pattern.compile(regexBuilder.toString());
        } else {
            pattern = Pattern.compile(nameMask);
        }
        this.workerStorage.getWorkerList().stream()
                .filter(worker -> pattern.matcher(worker.getName()).matches())
                .forEach(result::add);
        return result;
    }

    private List<Worker> getFilteredByNumberWorkerList(String field) {
        List<Worker> result = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        System.out.println(String.format("Введите значение для %s (к примеру, '1.5'):", field));
        Double numberFilter = null;
        boolean isNumber;
        do {
            try {
                isNumber = true;
                numberFilter = Double.valueOf(scanner.nextLine());
            } catch (NumberFormatException nfe) {
                isNumber = false;
                System.out.println("Введенное значение не является числом. Попробуйте снова.");
            }
        } while (!isNumber);
        boolean isComparisonSymbol;
        do {
            System.out.println("Введи знак условия: ('<', '>', '<=', '>=', '=', '!=')");
            String rateFilterString = scanner.nextLine();
            Pattern pattern = Pattern.compile("[<>=]|<=|>=|!=");
            if (pattern.matcher(rateFilterString).matches()) {
                isComparisonSymbol = true;
                Double finalNumberFilter = numberFilter;
                if (">".equals(rateFilterString)) {
                    if ("rate".equals(field)) {
                        this.workerStorage.getWorkerList().stream()
                                .filter(worker -> worker.getRate() > finalNumberFilter)
                                .forEach(result::add);
                    }
                    if ("experience".equals(field)) {
                        this.workerStorage.getWorkerList().stream()
                                .filter(worker -> worker.getExperience() > finalNumberFilter)
                                .forEach(result::add);
                    }
                }
                if ("<".equals(rateFilterString)) {
                    if ("rate".equals(field)) {
                        this.workerStorage.getWorkerList().stream()
                                .filter(worker -> worker.getRate() < finalNumberFilter)
                                .forEach(result::add);
                    }
                    if ("experience".equals(field)) {
                        this.workerStorage.getWorkerList().stream()
                                .filter(worker -> worker.getExperience() < finalNumberFilter)
                                .forEach(result::add);
                    }
                }
                if (">=".equals(rateFilterString)) {
                    if ("rate".equals(field)) {
                        this.workerStorage.getWorkerList().stream()
                                .filter(worker -> worker.getRate() >= finalNumberFilter)
                                .forEach(result::add);
                    }
                    if ("experience".equals(field)) {
                        this.workerStorage.getWorkerList().stream()
                                .filter(worker -> worker.getExperience() >= finalNumberFilter)
                                .forEach(result::add);
                    }
                }
                if ("<=".equals(rateFilterString)) {
                    if ("rate".equals(field)) {
                        this.workerStorage.getWorkerList().stream()
                                .filter(worker -> worker.getRate() <= finalNumberFilter)
                                .forEach(result::add);
                    }
                    if ("experience".equals(field)) {
                        this.workerStorage.getWorkerList().stream()
                                .filter(worker -> worker.getExperience() <= finalNumberFilter)
                                .forEach(result::add);
                    }
                }
                if ("=".equals(rateFilterString)) {
                    if ("rate".equals(field)) {
                        this.workerStorage.getWorkerList().stream()
                                .filter(worker -> worker.getRate() == finalNumberFilter)
                                .forEach(result::add);
                    }
                    if ("experience".equals(field)) {
                        this.workerStorage.getWorkerList().stream()
                                .filter(worker -> worker.getExperience() == finalNumberFilter)
                                .forEach(result::add);
                    }
                }
                if ("!=".equals(rateFilterString)) {
                    if ("rate".equals(field)) {
                        this.workerStorage.getWorkerList().stream()
                                .filter(worker -> worker.getRate() != finalNumberFilter)
                                .forEach(result::add);
                    }
                    if ("experience".equals(field)) {
                        this.workerStorage.getWorkerList().stream()
                                .filter(worker -> worker.getExperience() != finalNumberFilter)
                                .forEach(result::add);
                    }
                }
            } else {
                isComparisonSymbol = false;
                System.out.println("Неверный знак условия. Попробуйте еще раз.");
            }
        } while (!isComparisonSymbol);
        return result;
    }

    private void showFilterKeys() {
        System.out.println("Введи ключ фильтра или \"exit\" для выхода:");
        this.filterKeys.forEach((key, field) ->
                System.out.println("ключ " + key + " - " + field)
        );
    }

    private void showConverterKeys() {
        System.out.println("Введи ключ операции или \"exit\" для выхода:");
        this.converters.forEach((s, converter) ->
                System.out.println("ключ " + s + " для формата " + converter.toString())
        );
    }

    public static void main(String[] args) throws Exception {
        WorkerStorage workerStorage = new WorkerStorage();
        workerStorage.add(new Worker("worker1", 1.0, 1));
        workerStorage.add(new Worker("worker2", 2.0, 2));
        workerStorage.add(new Worker("worker2", 2.0, 3));
        workerStorage.add(new Worker("worker3", 3.0, 3));
        WorkerInfo workerInfo = new WorkerInfo(workerStorage);
        workerInfo.addConverter("1", new ToJSON());
        workerInfo.addConverter("2", new ToXML());
        workerInfo.addConverter("3", new ToTXT());
        workerInfo.startShow();
    }
}
