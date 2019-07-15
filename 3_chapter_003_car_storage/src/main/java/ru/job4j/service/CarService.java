package ru.job4j.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.dao.CarDaoImpl;
import ru.job4j.dao.EngineDaoImpl;
import ru.job4j.entity.annotations.Car;

import java.util.List;

public class CarService {
    private static final Logger LOG = LogManager.getLogger(EngineDaoImpl.class.getName());
    private static final CarDaoImpl CAR_DAO = CarDaoImpl.getInstance();
    private static final CarService INSTANCE = new CarService();

    public CarService() {
    }

    public CarService getInstance() {
        return INSTANCE;
    }
    public void save(Car car) {
        if (car != null) {
            CAR_DAO.save(car);
        }
    }
    public void update(Car car) {
        if (car != null) {
            CAR_DAO.update(car);
        }
    }
    public void delete(Car car) {
        if (car != null) {
            CAR_DAO.delete(car);
        }
    }
    public List<Car> getCars() {
        return CAR_DAO.getEntities();
    }
}
