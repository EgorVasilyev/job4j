package ru.job4j.dao.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Component;
import ru.job4j.dao.EntityDao;
import ru.job4j.entity.user.User;

import java.util.List;
@Component
public class UserDaoImpl implements EntityDao<User> {
    private static final Logger LOG = LogManager.getLogger(UserDaoImpl.class.getName());
    private final HibernateTemplate template;

    @Autowired
    private UserDaoImpl(HibernateTemplate template) {
        this.template = template;
    }
    @Override
    public List<User> getEntities() {
        return this.template.loadAll(User.class);
    }

    public User getUserById(int id) {
        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("id", id));
        return (User) this.template.findByCriteria(criteria);
    }


    @Override
    public int save(User user) {
        template.save(user);
        LOG.info("save - " + user);
        return user.getId();
    }

    @Override
    public void update(User user) {
        template.update(user);
        LOG.info("update - " + user);
    }

    @Override
    public void delete(User user) {
        template.delete(user);
        LOG.info("delete - " + user);
    }
}
