/*
package ru.job4j.dao.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.job4j.dao.EntityDao;
import ru.job4j.entity.car.Color;
import ru.job4j.entity.car.Engine;

import java.util.List;
@Repository
public class EngineDaoImpl implements EntityDao<Engine> {
    private static final Logger LOG = LogManager.getLogger(EngineDaoImpl.class.getName());
    @Autowired
    public SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Engine> getEntities() {
        return (List<Engine>) session().createQuery("from Engine order by id").list();
    }

    @Override
    public int save(Engine engine) {
        LOG.info("save - " + engine);
        return (int) session().save(engine);
    }

    @Override
    public void update(Engine engine) {
        session().update(engine);
        LOG.info("update - " + engine);
    }

    @Override
    public void delete(Engine engine) {
        session().delete(engine);
        LOG.info("delete - " + engine);
    }
}*/
