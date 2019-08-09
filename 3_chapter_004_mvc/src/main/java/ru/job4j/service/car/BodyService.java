package ru.job4j.service.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.dao.car.BodyDaoImpl;
import ru.job4j.entity.car.Body;

import java.util.List;
@Service
public class BodyService {
    private static final Logger LOG = LogManager.getLogger(BodyService.class.getName());
    private final BodyDaoImpl BODY_DAO;
    @Autowired
    public BodyService(BodyDaoImpl body_dao) {
        BODY_DAO = body_dao;
    }

    public int save(Body body) {
        int id = 0;
        if (body != null) {
            id = BODY_DAO.save(body);
        }
        return id;
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