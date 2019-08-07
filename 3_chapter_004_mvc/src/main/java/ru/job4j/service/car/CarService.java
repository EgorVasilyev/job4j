package ru.job4j.service.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.dao.car.CarDaoImpl;
import ru.job4j.entity.car.Car;

import java.util.List;
@Service
public class CarService {
    private static final Logger LOG = LogManager.getLogger(CarService.class.getName());
    private final CarDaoImpl CAR_DAO;
    @Autowired
    public CarService(CarDaoImpl car_dao) {
        CAR_DAO = car_dao;
    }

    public int save(Car car) {
        int id = 0;
        if (car != null) {
            id = CAR_DAO.save(car);
        }
        return id;
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
    public List<Car> getCarsByUserId(int userId) {
        return CAR_DAO.getCarsByUserId(userId);
    }
}
