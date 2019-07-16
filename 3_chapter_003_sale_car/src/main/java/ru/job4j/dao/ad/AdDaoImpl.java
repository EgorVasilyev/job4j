package ru.job4j.dao.ad;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import ru.job4j.dao.EntityDao;
import ru.job4j.entity.ad.Ad;
import ru.job4j.entity.car.Body;
import ru.job4j.entity.user.User;
import ru.job4j.persistent.DbProvider;

import java.util.List;

public class AdDaoImpl implements EntityDao<Ad> {
    private static final Logger LOG = LogManager.getLogger(AdDaoImpl.class.getName());
    private static final AdDaoImpl AD_DAO = new AdDaoImpl();
    private static final DbProvider DB_PROVIDER = DbProvider.getInstance();

    public static AdDaoImpl getInstance() {
        return AD_DAO;
    }

    private AdDaoImpl() {

    }

    @Override
    public List<Ad> getEntities() {
        return (List<Ad>) DB_PROVIDER.fn(
                session -> session.createQuery("from Ad order by id").list()
        );
    }

    public List<Ad> getNotClosedAds() {
        return (List<Ad>) DB_PROVIDER.fn(
                session -> session.createQuery("from Ad where closed = false order by id").list()
        );
    }

    public List<Ad> getAdsByUserId(int userId) {
        return DB_PROVIDER.fn(
                session -> {
                    Query<Ad> query = session.createQuery("from Ad where client = :userId", Ad.class);
                    query.setParameter("userId", userId);
                    return query.getResultList();
                }
        );
    }

    public Ad getAdById(int id) {
        return DB_PROVIDER.fn(
                session -> {
                    Query<Ad> query = session.createQuery("from Ad where id = :id", Ad.class);
                    query.setParameter("id", id);
                    return query.getSingleResult();
                }
        );
    }

    @Override
    public int save(Ad ad) {
        return (int) DB_PROVIDER.fn(
                session -> {
                    LOG.info("save - " + ad);
                    return session.save(ad);
                }
        );
    }

    @Override
    public void update(Ad ad) {
        DB_PROVIDER.cn(
                session -> {
                    session.update(ad);
                    LOG.info("update - " + ad);
                }
        );
    }

    @Override
    public void delete(Ad ad) {
        DB_PROVIDER.cn(
                session -> {
                    session.delete(ad);
                    LOG.info("delete - " + ad);
                }
        );
    }
}
