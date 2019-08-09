package ru.job4j.dao.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import ru.job4j.dao.EntityDao;
import ru.job4j.entity.ad.Ad;
import ru.job4j.entity.car.Body;
import ru.job4j.entity.car.Car;

import java.util.List;
@Repository
public class CarDaoImpl implements EntityDao<Car> {
    private static final Logger LOG = LogManager.getLogger(CarDaoImpl.class.getName());
    @Autowired
    public SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<Car> getEntities() {
        return (List<Car>) session().createQuery("from Car order by id").list();
    }

    @Override
    public int save(Car car) {
        LOG.info("save - " + car);
        return (int) session().save(car);
    }

    @Override
    public void update(Car car) {
        session().update(car);
        LOG.info("update - " + car);
    }

    @Override
    public void delete(Car car) {
        session().delete(car);
        LOG.info("delete - " + car);
    }

    public List<Car> getCarsByUserId(int userId) {
        Query<Car> query = session().createQuery("from Car where client = :userId order by id", Car.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }
}