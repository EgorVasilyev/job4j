package ru.job4j.dao.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.dao.EntityDao;
import ru.job4j.entity.car.Car;
import ru.job4j.persistent.DbProvider;

import java.util.List;
@Component
public class CarDaoImpl implements EntityDao<Car> {
    private static final Logger LOG = LogManager.getLogger(CarDaoImpl.class.getName());
    private final DbProvider DB_PROVIDER;
    @Autowired
    private CarDaoImpl(DbProvider db_provider) {
        DB_PROVIDER = db_provider;
    }

    @Override
    public List<Car> getEntities() {
        return (List<Car>) DB_PROVIDER.fn(
                session -> session.createQuery("from Car order by id").list()
        );
    }

    public List<Car> getCarsByUserId(int userId) {
        return DB_PROVIDER.fn(
                session -> {
                    Query<Car> query = session.createQuery("from Car where client = :userId", Car.class);
                    query.setParameter("userId", userId);
                    return query.getResultList();
                }
        );
    }

    @Override
    public int save(Car car) {
        return (int) DB_PROVIDER.fn(
                session -> {
                    LOG.info("save - " + car);
                    return session.save(car);
                }
        );
    }

    @Override
    public void update(Car car) {
        DB_PROVIDER.cn(
                session -> {
                    session.update(car);
                    LOG.info("update - " + car);
                }
        );
    }

    @Override
    public void delete(Car car) {
        DB_PROVIDER.cn(
                session -> {
                    session.delete(car);
                    LOG.info("delete - " + car);
                }
        );
    }
}
