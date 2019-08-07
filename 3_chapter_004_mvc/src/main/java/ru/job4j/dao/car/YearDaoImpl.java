package ru.job4j.dao.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import ru.job4j.dao.EntityDao;
import ru.job4j.entity.car.Year;

import java.util.List;
@Component
public class YearDaoImpl implements EntityDao<Year> {
    private static final Logger LOG = LogManager.getLogger(YearDaoImpl.class.getName());
    private final HibernateTemplate template;

    @Autowired
    private YearDaoImpl(HibernateTemplate template) {
        this.template = template;
    }
    @Override
    public List<Year> getEntities() {
        return this.template.loadAll(Year.class);
    }

    @Override
    public int save(Year year) {
        template.save(year);
        LOG.info("save - " + year);
        return year.getId();
    }

    @Override
    public void update(Year year) {
        template.update(year);
        LOG.info("update - " + year);
    }

    @Override
    public void delete(Year year) {
        template.delete(year);
        LOG.info("delete - " + year);
    }
}
