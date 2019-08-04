package ru.job4j.dao.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.job4j.dao.EntityDao;
import ru.job4j.entity.user.User;
import ru.job4j.persistent.DbProvider;

import java.util.List;
@Component
public class UserDaoImpl implements EntityDao<User> {
    private static final Logger LOG = LogManager.getLogger(UserDaoImpl.class.getName());
    private final DbProvider DB_PROVIDER;
    @Autowired
    public UserDaoImpl(DbProvider db_provider) {
        DB_PROVIDER = db_provider;
    }

    @Override
    public List<User> getEntities() {
        return (List<User>) DB_PROVIDER.fn(
                session -> session.createQuery("from User  order by id").list()
        );
        /*where login != 'guest'*/
    }

    public User getUserById(int id) {
        return DB_PROVIDER.fn(
                session -> {
                    Query<User> query = session.createQuery("from User where id = :id", User.class);
                    query.setParameter("id", id);
                    return query.getSingleResult();
                }
        );
    }


    @Override
    public int save(User user) {
        return (int) DB_PROVIDER.fn(
                session -> {
                    LOG.info("save - " + user);
                    return session.save(user);
                }
        );
    }

    @Override
    public void update(User user) {
        DB_PROVIDER.cn(
                session -> {
                    session.update(user);
                    LOG.info("update - " + user);
                }
        );
    }

    @Override
    public void delete(User user) {
        DB_PROVIDER.cn(
                session -> {
                    session.delete(user);
                    LOG.info("delete - " + user);
                }
        );
    }
}
