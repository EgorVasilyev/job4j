package ru.job4j.service.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.dao.car.YearDaoImpl;
import ru.job4j.entity.car.Year;

import java.util.List;
@Service
public class YearService {
    private static final Logger LOG = LogManager.getLogger(YearService.class.getName());
    private final YearDaoImpl YEAR_DAO;
    @Autowired
    public YearService(YearDaoImpl year_dao) {
        YEAR_DAO = year_dao;
    }

    public int save(Year year) {
        int id = 0;
        if (year != null) {
            id = YEAR_DAO.save(year);
        }
        return id;
    }
    public void update(Year year) {
        if (year != null) {
            YEAR_DAO.update(year);
        }
    }
    public void delete(Year year) {
        if (year != null) {
            YEAR_DAO.delete(year);
        }
    }
    public List<Year> getYears() {
        return YEAR_DAO.getEntities();
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
