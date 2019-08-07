package ru.job4j.dao.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import ru.job4j.dao.EntityDao;
import ru.job4j.entity.car.Engine;

import java.util.List;
@Component
public class EngineDaoImpl implements EntityDao<Engine> {
    private static final Logger LOG = LogManager.getLogger(EngineDaoImpl.class.getName());
    private final HibernateTemplate template;

    @Autowired
    private EngineDaoImpl(HibernateTemplate template) {
        this.template = template;
    }
    @Override
    public List<Engine> getEntities() {
        return this.template.loadAll(Engine.class);
    }

    @Override
    public int save(Engine engine) {
        template.save(engine);
        LOG.info("save - " + engine);
        return engine.getId();
    }

    @Override
    public void update(Engine engine) {
        template.update(engine);
        LOG.info("update - " + engine);
    }

    @Override
    public void delete(Engine engine) {
        template.delete(engine);
        LOG.info("delete - " + engine);
    }
}
