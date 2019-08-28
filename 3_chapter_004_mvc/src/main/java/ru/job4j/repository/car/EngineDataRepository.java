package ru.job4j.repository.car;

import org.springframework.data.repository.CrudRepository;
import ru.job4j.domain.car.Engine;

import java.util.List;

public interface EngineDataRepository extends CrudRepository<Engine, Integer> {
    List<Engine> findAllByOrderByIdAsc();
}
