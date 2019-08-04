package ru.job4j.dao.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.dao.EntityDao;
import ru.job4j.entity.car.Body;
import ru.job4j.persistent.DbProvider;

import java.util.List;
@Component
public class BodyDaoImpl implements EntityDao<Body> {
    private static final Logger LOG = LogManager.getLogger(BodyDaoImpl.class.getName());

    private final DbProvider DB_PROVIDER;
    @Autowired
    private BodyDaoImpl(DbProvider db_provider) {
        DB_PROVIDER = db_provider;
    }

    @Override
    public List<Body> getEntities() {
        return (List<Body>) DB_PROVIDER.fn(
                session -> session.createQuery("from Body order by id").list()
        );
    }

    @Override
    public int save(Body body) {
        return (int) DB_PROVIDER.fn(
                session -> {
                    LOG.info("save - " + body);
                    return session.save(body);
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
