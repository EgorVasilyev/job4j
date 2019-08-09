package ru.job4j.dao.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.dao.EntityDao;
import ru.job4j.entity.ad.Ad;
import ru.job4j.entity.car.Year;
import ru.job4j.entity.user.User;

import java.util.List;
@Component
public class UserDaoImpl implements EntityDao<User> {
    private static final Logger LOG = LogManager.getLogger(UserDaoImpl.class.getName());
    @Autowired
    public SessionFactory sessionFactory;

    private Session session() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List<User> getEntities() {
        return (List<User>) session().createQuery("from User order by id").list();
    }

    @Override
    public int save(User user) {
        LOG.info("save - " + user);
        return (int) session().save(user);
    }

    @Override
    public void update(User user) {
        session().update(user);
        LOG.info("update - " + user);
    }

    @Override
    public void delete(User user) {
        session().delete(user);
        LOG.info("delete - " + user);
    }

    public User getUserById(int id) {
        Query<User> query = session().createQuery("from User where id = :id", User.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }
}