package ru.job4j.service.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.car.Engine;
import ru.job4j.repository.car.EngineDataRepository;

import java.util.List;
@Service
public class EngineService {
    private final EngineDataRepository engineDataRepository;
    @Autowired
    public EngineService(EngineDataRepository engineDataRepository) {
        this.engineDataRepository = engineDataRepository;
    }

    public int save(Engine engine) {
        int id = 0;
        if (engine != null) {
            engineDataRepository.save(engine);
            id = engine.getId();
        }
        return id;
    }
    public void update(Engine engine) {
        if (engine != null) {
            engineDataRepository.save(engine);
        }
    }
    public void delete(Engine engine) {
        if (engine != null) {
            engineDataRepository.delete(engine);
        }
    }
    public List<Engine> getEngines() {
        return engineDataRepository.findAllByOrderByIdAsc();
    }
    public boolean contains(String name) {
        return this.getEngines().stream().anyMatch(engineHas -> engineHas.getName().toLowerCase().equals(name.toLowerCase()));
    }
    public Engine getByName(String name) {
        return this.getEngines()
                .stream()
                .filter(engine -> engine.getName().toLowerCase().equals(name.toLowerCase()))
                .findFirst()
                .orElse(null);
    }
}
