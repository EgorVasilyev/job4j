package ru.job4j.dao.ad;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.dao.EntityDao;
import ru.job4j.entity.ad.Ad;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;
@Component
public class AdDaoImpl implements EntityDao<Ad> {
    private static final Logger LOG = LogManager.getLogger(AdDaoImpl.class.getName());
    @Autowired
    private SessionFactory sessionFactory;

    private AdDaoImpl() {

    }

    private Session session() {
        return sessionFactory.getCurrentSession();
    }
    @Override
    public int save(Ad ad) {
        session().save(ad);
        LOG.info("save - " + ad);
        return ad.getId();
    }
    @Transactional
    @Override
    public void update(Ad ad) {
        session().update(ad);
        LOG.info("update - " + ad);
    }
    @Transactional
    @Override
    public void delete(Ad ad) {
        session().delete(ad);
        LOG.info("delete - " + ad);
    }
    @Transactional
    @Override
    public List<Ad> getEntities() {
        return (List<Ad>) this.session().createQuery("from Ad order by id").list();
    }

    public List<Ad> getAdsByUserId(int userId) {
        Query<Ad> query = this.session().createQuery("from Ad where client = :userId order by id", Ad.class);
        query.setParameter("userId", userId);
        return query.getResultList();
    }

    public Ad getAdById(int id) {
        Query<Ad> query = this.session().createQuery("from Ad where id = :id", Ad.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public List<Ad> getNotClosedAds() {
        return (List<Ad>) this.session().createQuery("from Ad where closed = false order by id").list();
    }

    public List<Ad> getAdsByFilter(Map<String, String> filter) {
        CriteriaBuilder builder = this.session().getCriteriaBuilder();
        CriteriaQuery<Ad> criteriaQuery = builder.createQuery(Ad.class);
        Root<Ad> ads = criteriaQuery.from(Ad.class);

        criteriaQuery.where(getPredicates(builder, ads, filter));
        criteriaQuery.orderBy(builder.asc(ads.get("id")));

        return this.session().createQuery(criteriaQuery).getResultList();
    }

    private Predicate[] getPredicates(CriteriaBuilder builder, Root<Ad> ads, Map<String, String> filter) {
        List<Predicate> predicateList = new ArrayList<>();
        for (String key : filter.keySet()) {
            String value = filter.get(key);
            if (key.equals("currentDay")) {
                Calendar calendar = Calendar.getInstance();
                predicateList.add(
                        builder.and(
                                builder.equal(
                                        builder.function("year", Integer.class, ads.get("created")),
                                        calendar.get(Calendar.YEAR)
                                ),
                                builder.equal(
                                        builder.function("month", Integer.class, ads.get("created")),
                                        calendar.get(Calendar.MONTH) + 1
                                ),
                                builder.equal(
                                        builder.function("day", Integer.class, ads.get("created")),
                                        calendar.get(Calendar.DAY_OF_MONTH)
                                )
                        )
                );
            }
            if (key.equals("withPhoto")) {
                predicateList.add(builder.isNotNull(ads.get("picture")));
            }
            if (key.equals("actual")) {
                predicateList.add(builder.isFalse(ads.get("closed")));
            }
            if (key.equals("byName")) {
                predicateList.add(builder.like((ads.get("car").get("name")), "%" + value + "%"));
            }
        }
        Predicate[] predicates = new Predicate[predicateList.size()];
        predicateList.toArray(predicates);
        return predicates;
    }
}
