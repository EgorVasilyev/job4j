package ru.job4j.repository.car;

        import org.springframework.data.repository.CrudRepository;
import ru.job4j.entity.car.Body;

import java.util.List;

public interface BodyDataRepository extends CrudRepository<Body, Integer> {
    List<Body> findAllByOrderByIdAsc();
}
