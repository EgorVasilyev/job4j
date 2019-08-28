package ru.job4j.repository.car;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.car.Color;

import java.util.List;

public interface ColorDataRepository extends CrudRepository<Color, Integer> {
    List<Color> findAllByOrderByIdAsc();
}
