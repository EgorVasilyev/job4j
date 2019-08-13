package ru.job4j.service.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.entity.car.Car;
import ru.job4j.repository.car.CarDataRepository;

import java.util.List;
@Service
public class CarService {
    private final CarDataRepository carDataRepository;
    @Autowired
    public CarService(CarDataRepository carDataRepository) {
        this.carDataRepository = carDataRepository;
    }

    public int save(Car car) {
        int id = 0;
        if (car != null) {
            carDataRepository.save(car);
            id = car.getId();
        }
        return id;
    }
    public void update(Car car) {
        if (car != null) {
            carDataRepository.save(car);
        }
    }
    public void delete(Car car) {
        if (car != null) {
            carDataRepository.delete(car);
        }
    }
    public List<Car> getCars() {
        return carDataRepository.findAllByOrderByIdAsc();
    }
    public List<Car> getCarsByUserId(int userId) {
        return carDataRepository.findCarsByUserIdOrderByIdAsc(userId);
    }
}
