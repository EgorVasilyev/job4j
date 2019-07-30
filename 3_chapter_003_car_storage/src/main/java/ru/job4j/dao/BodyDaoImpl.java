package ru.job4j.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.entity.annotations.Body;
import ru.job4j.persistent.DbProvider;

import java.util.List;

public class BodyDaoImpl implements EntityDao<Body> {
    private static final Logger LOG = LogManager.getLogger(BodyDaoImpl.class.getName());
    private static final BodyDaoImpl BODY_DAO = new BodyDaoImpl();
    private static final DbProvider DB_PROVIDER = DbProvider.getInstance();

    public static BodyDaoImpl getInstance() {
        return BODY_DAO;
    }

    private BodyDaoImpl() {

    }

    @Override
    public List<Body> getEntities() {
        return (List<Body>) DB_PROVIDER.fn(
                session -> session.createQuery("from Body order by id").list()
        );
    }


    @Override
    public void save(Body body) {
        DB_PROVIDER.cn(
                session -> {
                    session.save(body);
                    LOG.info("save - " + body);
                }
        );
    }

    @Override
    public void update(Body body) {
        DB_PROVIDER.cn(
                session -> {
                    session.update(body);
                    LOG.info("update - " + body);
                }
        );
    }

    @Override
    public void delete(Body body) {
        DB_PROVIDER.cn(
                session -> {
                    session.delete(body);
                    LOG.info("delete - " + body);
                }
        );
    }
}
