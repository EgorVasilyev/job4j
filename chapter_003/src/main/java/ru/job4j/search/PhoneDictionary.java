package ru.job4j.search;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class PhoneDictionary {
    private List<Person> persons = new ArrayList<Person>();

    public void add(Person person) {
        this.persons.add(person);
    }

    /**
     * Вернуть список всех пользователей, который содержат key в любых полях.
     * @param key Ключ поиска.
     * @return Список подощедщих пользователей.
     */
    public List<Person> find(String key) {
        List<Person> result = new ArrayList<>();
        persons.stream()
                .filter(person -> person.getAddress().contains(key))
                .forEach(result::add);
        persons.stream()
                .filter(person -> person.getName().contains(key))
                .forEach(result::add);
        persons.stream()
                .filter(person -> person.getPhone().contains(key))
                .forEach(result::add);
        persons.stream()
                .filter(person -> person.getSurname().contains(key))
                .forEach(result::add);
        result = result.stream()
                .distinct()
                .collect(Collectors.toList());
        return result;
    }
}