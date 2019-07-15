package ru.job4j.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.dao.EngineDaoImpl;
import ru.job4j.entity.annotations.Engine;

import java.util.List;

public class EngineService {
    private static final Logger LOG = LogManager.getLogger(EngineDaoImpl.class.getName());
    private static final EngineDaoImpl ENGINE_DAO = EngineDaoImpl.getInstance();
    private static final EngineService INSTANCE = new EngineService();

    public EngineService() {
    }

    public EngineService getInstance() {
        return INSTANCE;
    }
    public void save(Engine engine) {
        if (engine != null) {
            ENGINE_DAO.save(engine);
        }
    }
    public void update(Engine engine) {
        if (engine != null) {
            ENGINE_DAO.update(engine);
        }
    }
    public void delete(Engine engine) {
        if (engine != null) {
            ENGINE_DAO.delete(engine);
        }
    }
    public List<Engine> getEngines() {
        return ENGINE_DAO.getEntities();
    }
}
