package ru.job4j.dao.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.dao.EntityDao;
import ru.job4j.entity.car.Color;
import ru.job4j.persistent.DbProvider;

import java.util.List;
@Component
public class ColorDaoImpl implements EntityDao<Color> {
    private static final Logger LOG = LogManager.getLogger(ColorDaoImpl.class.getName());
    private final DbProvider DB_PROVIDER;
    @Autowired
    private ColorDaoImpl(DbProvider db_provider) {

        DB_PROVIDER = db_provider;
    }

    @Override
    public List<Color> getEntities() {
        return (List<Color>) DB_PROVIDER.fn(
                session -> session.createQuery("from Color order by id").list()
        );
    }

    @Override
    public int save(Color color) {
        return (int) DB_PROVIDER.fn(
                session -> {
                    LOG.info("save - " + color);
                    return session.save(color);
                }
        );
    }

    @Override
    public void update(Color color) {
        DB_PROVIDER.cn(
                session -> {
                    session.update(color);
                    LOG.info("update - " + color);
                }
        );
    }

    @Override
    public void delete(Color color) {
        DB_PROVIDER.cn(
                session -> {
                    session.delete(color);
                    LOG.info("delete - " + color);
                }
        );
    }
}
