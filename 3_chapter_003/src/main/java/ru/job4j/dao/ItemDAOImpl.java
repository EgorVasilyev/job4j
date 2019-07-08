package ru.job4j.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import ru.job4j.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    private static final Logger LOG = LogManager.getLogger(ItemDAOImpl.class.getName());
    private static ItemDAOImpl INSTANCE = new ItemDAOImpl();
    private final SessionFactory sessionFactory;

    public static ItemDAOImpl getInstance() {
        return INSTANCE;
    }

    private ItemDAOImpl() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public List<Item> getItems() {
        List<Item> list = new ArrayList<>();
        final Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        try {
            list = session.createQuery("from Item", Item.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Error", e);
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public List<Item> getDoneItems() {
        List<Item> list = new ArrayList<>();
        final Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        try {
            list = session.createQuery("from Item where done = true", Item.class).list();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Error", e);
        } finally {
            session.close();
        }
        return list;
    }

    @Override
    public void save(Item task) {
        final Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        try {
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Error", e);
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Item task) {
        final Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        try {
            session.update(task);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Error", e);
        } finally {
            session.close();
        }
    }
}
