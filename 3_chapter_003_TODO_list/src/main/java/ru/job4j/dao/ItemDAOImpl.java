package ru.job4j.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import ru.job4j.entity.Item;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class ItemDAOImpl implements ItemDAO {
    private static final Logger LOG = LogManager.getLogger(ItemDAOImpl.class.getName());
    private static final ItemDAOImpl ITEM_DAO = new ItemDAOImpl();
    private final SessionFactory sessionFactory;

    public static ItemDAOImpl getInstance() {
        return ITEM_DAO;
    }

    private ItemDAOImpl() {
        sessionFactory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public List<Item> getItems() {
        return (List<Item>) this.tx(
                session -> session.createQuery("from Item order by id").list()
        );
    }

    @Override
    public List<Item> getNotDoneItems() {
        return (List<Item>) this.tx(
                session -> session.createQuery("from Item where done = false order by id").list()
        );
    }

    @Override
    public Item getById(int id) {
        return this.tx(
                session -> {
                    Query<Item> query = session.createQuery("from Item where id = :id", Item.class);
                    query.setParameter("id", id);
                    return query.getSingleResult();
                }
        );
    }

    @Override
    public void save(Item item) {
        this.cn(
                session ->
                        session.save(item)
        );
    }

    @Override
    public void update(Item item) {
        this.cn(
                session ->
                        session.update(item)
        );
    }

    @Override
    public void delete(Item item) {
        this.cn(
                session ->
                        session.delete(item)
        );
    }

    private <T> T tx(final Function<Session, T> command) {
        final Session session = this.sessionFactory.getCurrentSession();
        final Transaction transaction = session.beginTransaction();
        try {
            T rsl = command.apply(session);
            transaction.commit();
            return rsl;
        } catch (final Exception e) {
            session.getTransaction().rollback();
            LOG.error("Error", e);
            throw e;
        } finally {
            session.close();
        }
    }
    private void cn(final Consumer<Session> command) {
        final Session session = this.sessionFactory.getCurrentSession();
        final Transaction transaction = session.beginTransaction();
        try {
            command.accept(session);
            transaction.commit();
        } catch (final Exception e) {
            session.getTransaction().rollback();
            LOG.error("Error", e);
            throw e;
        } finally {
            session.close();
        }
    }
}
