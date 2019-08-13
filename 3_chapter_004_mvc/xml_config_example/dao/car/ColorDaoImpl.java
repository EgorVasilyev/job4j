/*
package ru.job4j.dao.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.dao.EntityDao;
import ru.job4j.entity.car.Body;
import ru.job4j.entity.car.Color;

import java.util.List;
@Component
public class ColorDaoImpl implements EntityDao<Color> {
    private static final Logger LOG = LogManager.getLogger(ColorDaoImpl.class.getName());
    @Autowired
    public SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Color> getEntities() {
        return (List<Color>) session().createQuery("from Color order by id").list();
    }

    @Override
    public int save(Color color) {
        LOG.info("save - " + color);
        return (int) session().save(color);
    }

    @Override
    public void update(Color color) {
        session().update(color);
        LOG.info("update - " + color);
    }

    @Override
    public void delete(Color color) {
        session().delete(color);
        LOG.info("delete - " + color);
    }
}*/
