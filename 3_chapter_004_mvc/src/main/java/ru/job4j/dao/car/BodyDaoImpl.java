package ru.job4j.dao.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.job4j.dao.EntityDao;
import ru.job4j.entity.ad.Ad;
import ru.job4j.entity.car.Body;

import java.util.List;
@Repository
public class BodyDaoImpl implements EntityDao<Body> {
    private static final Logger LOG = LogManager.getLogger(BodyDaoImpl.class.getName());
    @Autowired
    public SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Body> getEntities() {
        return (List<Body>) session().createQuery("from Body order by id").list();
    }

    @Override
    public int save(Body body) {
        LOG.info("save - " + body);
        return (int) session().save(body);
    }

    @Override
    public void update(Body body) {
        session().update(body);
        LOG.info("update - " + body);
    }

    @Override
    public void delete(Body body) {
        session().delete(body);
        LOG.info("delete - " + body);
    }
}