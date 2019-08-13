/*
package ru.job4j.dao.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.job4j.dao.EntityDao;
import ru.job4j.entity.car.Year;

import java.util.List;
@Repository
public class YearDaoImpl implements EntityDao<Year> {
    private static final Logger LOG = LogManager.getLogger(YearDaoImpl.class.getName());
    @Autowired
    public SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Year> getEntities() {
        return (List<Year>) session().createQuery("from Year order by id").list();
    }

    @Override
    public int save(Year year) {
        LOG.info("save - " + year);
        return (int) session().save(year);
    }

    @Override
    public void update(Year year) {
        session().update(year);
        LOG.info("update - " + year);
    }

    @Override
    public void delete(Year year) {
        session().delete(year);
        LOG.info("delete - " + year);
    }
}*/
