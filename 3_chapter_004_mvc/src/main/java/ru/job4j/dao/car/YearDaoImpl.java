package ru.job4j.dao.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.dao.EntityDao;
import ru.job4j.entity.car.Year;
import ru.job4j.persistent.DbProvider;

import java.util.List;
@Component
public class YearDaoImpl implements EntityDao<Year> {
    private static final Logger LOG = LogManager.getLogger(YearDaoImpl.class.getName());
    private final DbProvider DB_PROVIDER;
    @Autowired
    public YearDaoImpl(DbProvider db_provider) {
        DB_PROVIDER = db_provider;
    }

    @Override
    public List<Year> getEntities() {
        return (List<Year>) DB_PROVIDER.fn(
                session -> session.createQuery("from Year order by id").list()
        );
    }

    @Override
    public int save(Year year) {
        return (int) DB_PROVIDER.fn(
                session -> {
                    LOG.info("save - " + year);
                    return session.save(year);
                }
        );
    }

    @Override
    public void update(Year year) {
        DB_PROVIDER.cn(
                session -> {
                    session.update(year);
                    LOG.info("update - " + year);
                }
        );
    }

    @Override
    public void delete(Year year) {
        DB_PROVIDER.cn(
                session -> {
                    session.delete(year);
                    LOG.info("delete - " + year);
                }
        );
    }
}
