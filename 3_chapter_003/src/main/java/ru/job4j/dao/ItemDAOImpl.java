package ru.job4j.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.job4j.entity.Item;

import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    private static final Logger LOG = LogManager.getLogger(ItemDAOImpl.class.getName());
    private static ItemDAOImpl itemDAO = new ItemDAOImpl();
    private final SessionFactory sessionFactory;

    public static ItemDAOImpl getInstance() {
        return itemDAO;
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
            list = session.createQuery("from Item order by id", Item.class).list();
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
    public List<Item> getNotDoneItems() {
        List<Item> list = new ArrayList<>();
        final Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        try {
            list = session.createQuery("from Item where done = false order by id", Item.class).list();
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
    public Item getById(int id) {
        Item item = new Item();
        final Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        try {
            Query<Item> query = session.createQuery("from Item where id = :id", Item.class);
            query.setParameter("id", id);
            item = query.getSingleResult();
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Error", e);
        } finally {
            session.close();
        }
        return item;
    }

    @Override
    public void save(Item item) {
        final Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        try {
            session.save(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Error", e);
        } finally {
            session.close();
        }
    }

    @Override
    public void update(Item item) {
        final Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        try {
            session.update(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Error", e);
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Item item) {
        final Session session = this.sessionFactory.getCurrentSession();
        session.beginTransaction();
        try {
            session.delete(item);
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            LOG.error("Error", e);
        } finally {
            session.close();
        }
    }
}
