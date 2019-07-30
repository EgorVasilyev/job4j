package ru.job4j.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.entity.annotations.Car;
import ru.job4j.persistent.DbProvider;

import java.util.List;

public class CarDaoImpl implements EntityDao<Car> {
    private static final Logger LOG = LogManager.getLogger(CarDaoImpl.class.getName());
    private static final CarDaoImpl CAR_DAO = new CarDaoImpl();
    private static final DbProvider DB_PROVIDER = DbProvider.getInstance();

    public static CarDaoImpl getInstance() {
        return CAR_DAO;
    }

    private CarDaoImpl() {
    }

    @Override
    public List<Car> getEntities() {
        return (List<Car>) DB_PROVIDER.fn(
                session -> session.createQuery("from Car order by id").list()
        );
    }


    @Override
    public void save(Car car) {
        DB_PROVIDER.cn(
                session -> {
                    session.save(car);
                    LOG.info("save - " + car);
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
