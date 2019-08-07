package ru.job4j.dao.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import ru.job4j.dao.EntityDao;
import ru.job4j.entity.car.Body;

import java.util.List;
@Component
public class BodyDaoImpl implements EntityDao<Body> {
    private static final Logger LOG = LogManager.getLogger(BodyDaoImpl.class.getName());

    private final HibernateTemplate template;

    @Autowired
    private BodyDaoImpl(HibernateTemplate template) {
        this.template = template;
    }

    @Override
    public List<Body> getEntities() {
        return this.template.loadAll(Body.class);
    }

    @Override
    public int save(Body body) {
        template.save(body);
        LOG.info("save - " + body);
        return body.getId();
    }

    @Override
    public void update(Body body) {
        template.update(body);
        LOG.info("update - " + body);
    }

    @Override
    public void delete(Body body) {
        template.delete(body);
        LOG.info("delete - " + body);
    }
}
