package ru.job4j.dao.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.dao.EntityDao;
import ru.job4j.entity.car.Engine;
import ru.job4j.persistent.DbProvider;

import java.util.List;
@Component
public class EngineDaoImpl implements EntityDao<Engine> {
    private static final Logger LOG = LogManager.getLogger(EngineDaoImpl.class.getName());
    private final DbProvider DB_PROVIDER;
    @Autowired
    private EngineDaoImpl(DbProvider db_provider) {
        DB_PROVIDER = db_provider;
    }

    @Override
    public List<Engine> getEntities() {
        return (List<Engine>) DB_PROVIDER.fn(
                session -> session.createQuery("from Engine order by id").list()
        );
    }

    @Override
    public int save(Engine engine) {
        return (int) DB_PROVIDER.fn(
                session -> {
                    LOG.info("save - " + engine);
                    return session.save(engine);
                }
        );
    }

    @Override
    public void update(Engine engine) {
        DB_PROVIDER.cn(
                session -> {
                    session.update(engine);
                    LOG.info("update - " + engine);
                }
        );
    }

    @Override
    public void delete(Engine engine) {
        DB_PROVIDER.cn(
                session -> {
                    session.delete(engine);
                    LOG.info("delete - " + engine);
                }
        );
    }
}
