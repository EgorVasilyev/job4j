package ru.job4j.service.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.car.Year;
import ru.job4j.repository.car.YearDataRepository;

import java.util.List;
@Service
public class YearService {
    private final YearDataRepository yearDataRepository;
    @Autowired
    public YearService(YearDataRepository yearDataRepository) {
        this.yearDataRepository = yearDataRepository;
    }

    public int save(Year year) {
        int id = 0;
        if (year != null) {
            yearDataRepository.save(year);
            id = year.getId();
        }
        return id;
    }
    public void update(Year year) {
        if (year != null) {
            yearDataRepository.save(year);
        }
    }
    public void delete(Year year) {
        if (year != null) {
            yearDataRepository.delete(year);
        }
    }
    public List<Year> getYears() {
        return yearDataRepository.findAllByOrderByIdAsc();
    }
    public boolean contains(int yearValue) {
        return this.getYears().stream().anyMatch(yearHas -> yearHas.getValue() == yearValue);
    }
    public Year getByValue(int value) {
        return this.getYears()
                .stream()
                .filter(year -> year.getValue() == value)
                .findFirst()
                .orElse(null);
    }
}
