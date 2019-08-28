package ru.job4j.repository.car;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.car.Car;

import java.util.List;

public interface CarDataRepository extends CrudRepository<Car, Integer> {
    List<Car> findAllByOrderByIdAsc();
    List<Car> findCarsByUserIdOrderByIdAsc(int userId);
}
