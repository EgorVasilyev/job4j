package ru.job4j.repository.car;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.car.Year;

import java.util.List;

public interface YearDataRepository extends CrudRepository<Year, Integer> {
    List<Year> findAllByOrderByIdAsc();
}
