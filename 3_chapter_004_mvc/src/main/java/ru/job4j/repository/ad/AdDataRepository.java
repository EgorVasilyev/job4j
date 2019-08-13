package ru.job4j.repository.ad;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import ru.job4j.entity.ad.Ad;

import java.util.List;
public interface AdDataRepository extends CrudRepository<Ad, Integer>, JpaSpecificationExecutor<Ad> {
    List<Ad> findAllByOrderByIdAsc();
    Ad findAdById(int id);
    List<Ad> findAdsByUserIdOrderByIdAsc(int userId);
}
