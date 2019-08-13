package ru.job4j.specification;

import org.springframework.data.jpa.domain.Specification;
import ru.job4j.entity.ad.Ad;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.Calendar;

public class AdSpecification {
    public static Specification<Ad> orderById() {
        return new Specification<Ad>() {
            @Override
            public Predicate toPredicate(Root<Ad> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
                return criteriaQuery.getRestriction();
            }
        };
    }
    public static Specification<Ad> currentDay() {
        return new Specification<Ad>() {
            @Override
            public Predicate toPredicate(Root<Ad> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                Calendar calendar = Calendar.getInstance();
                return criteriaBuilder.and(
                        criteriaBuilder.equal(
                                criteriaBuilder.function("year", Integer.class, root.get("created")),
                                calendar.get(Calendar.YEAR)
                        ),
                        criteriaBuilder.equal(
                                criteriaBuilder.function("month", Integer.class, root.get("created")),
                                calendar.get(Calendar.MONTH) + 1
                        ),
                        criteriaBuilder.equal(
                                criteriaBuilder.function("day", Integer.class, root.get("created")),
                                calendar.get(Calendar.DAY_OF_MONTH)
                        )
                );
            }
        };
    }
    public static Specification<Ad> withPhoto() {
        return new Specification<Ad>() {
            @Override
            public Predicate toPredicate(Root<Ad> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.isNotNull(root.get("picture"));
            }
        };
    }
    public static Specification<Ad> actual() {
        return new Specification<Ad>() {
            @Override
            public Predicate toPredicate(Root<Ad> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.isFalse(root.get("closed"));
            }
        };
    }
    public static Specification<Ad> byName(final String name) {
        return new Specification<Ad>() {
            @Override
            public Predicate toPredicate(Root<Ad> root,
                                         CriteriaQuery<?> criteriaQuery,
                                         CriteriaBuilder criteriaBuilder) {
                return criteriaBuilder.like((root.get("car").get("name")), "%" + name + "%");
            }
        };
    }
}
