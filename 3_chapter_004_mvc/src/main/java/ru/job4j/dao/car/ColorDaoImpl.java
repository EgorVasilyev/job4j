package ru.job4j.dao.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import ru.job4j.dao.EntityDao;
import ru.job4j.entity.car.Color;

import java.util.List;
@Component
public class ColorDaoImpl implements EntityDao<Color> {
    private static final Logger LOG = LogManager.getLogger(ColorDaoImpl.class.getName());
    private final HibernateTemplate template;

    @Autowired
    private ColorDaoImpl(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public List<Color> getEntities() {
        return this.template.loadAll(Color.class);
    }

    @Override
    public int save(Color color) {
        template.save(color);
        LOG.info("save - " + color);
        return color.getId();
    }

    @Override
    public void update(Color color) {
        template.update(color);
        LOG.info("update - " + color);
    }

    @Override
    public void delete(Color color) {
        template.delete(color);
        LOG.info("delete - " + color);
    }
}
