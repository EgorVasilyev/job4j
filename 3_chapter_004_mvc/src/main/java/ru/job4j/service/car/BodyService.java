package ru.job4j.service.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.car.Body;
import ru.job4j.repository.car.BodyDataRepository;

import java.util.List;
@Service
public class BodyService {
    private final BodyDataRepository bodyDataRepository;
    @Autowired
    public BodyService(BodyDataRepository bodyDataRepository) {
        this.bodyDataRepository = bodyDataRepository;
    }

    public int save(Body body) {
        int id = 0;
        if (body != null) {
            bodyDataRepository.save(body);
            id = body.getId();
        }
        return id;
    }
    public void update(Body body) {
        if (body != null) {
            bodyDataRepository.save(body);
        }
    }
    public void delete(Body body) {
        if (body != null) {
            bodyDataRepository.delete(body);
        }
    }
    public List<Body> getBodies() {
        return bodyDataRepository.findAllByOrderByIdAsc();
    }
    public boolean contains(String name) {
        return this.getBodies().stream().anyMatch(bodyHas -> bodyHas.getName().toLowerCase().equals(name.toLowerCase()));
    }
    public Body getByName(String name) {
        return this.getBodies()
                .stream()
                .filter(body -> body.getName().toLowerCase().equals(name.toLowerCase()))
                .findFirst()
                .orElse(null);
    }
}
