package ru.job4j.dao.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import ru.job4j.dao.EntityDao;
import ru.job4j.entity.car.Car;

import java.util.List;
@Component
public class CarDaoImpl implements EntityDao<Car> {
    private static final Logger LOG = LogManager.getLogger(CarDaoImpl.class.getName());
    private final HibernateTemplate template;

    @Autowired
    private CarDaoImpl(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public List<Car> getEntities() {
        return this.template.loadAll(Car.class);
    }

    public List<Car> getCarsByUserId(int userId) {
        DetachedCriteria criteria = DetachedCriteria.forClass(Car.class);
        criteria.add(Restrictions.eq("client", userId));
        criteria.addOrder(Order.asc("id"));
        return (List<Car>) this.template.findByCriteria(criteria);
    }

    @Override
    public int save(Car car) {
        template.save(car);
        LOG.info("save - " + car);
        return car.getId();
    }

    @Override
    public void update(Car car) {
        template.update(car);
        LOG.info("update - " + car);
    }

    @Override
    public void delete(Car car) {
        template.delete(car);
        LOG.info("delete - " + car);
    }
}
