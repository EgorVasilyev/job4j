package ru.job4j.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.dao.BodyDaoImpl;
import ru.job4j.dao.EngineDaoImpl;
import ru.job4j.entity.annotations.Body;

import java.util.List;

public class BodyService {
    private static final Logger LOG = LogManager.getLogger(EngineDaoImpl.class.getName());
    private static final BodyDaoImpl BODY_DAO = BodyDaoImpl.getInstance();
    private static final BodyService INSTANCE = new BodyService();

    public BodyService() {
    }

    public BodyService getInstance() {
        return INSTANCE;
    }
    public void save(Body body) {
        if (body != null) {
            BODY_DAO.save(body);
        }
    }
    public void update(Body body) {
        if (body != null) {
            BODY_DAO.update(body);
        }
    }
    public void delete(Body body) {
        if (body != null) {
            BODY_DAO.delete(body);
        }
    }
    public List<Body> getBodies() {
        return BODY_DAO.getEntities();
    }
}
