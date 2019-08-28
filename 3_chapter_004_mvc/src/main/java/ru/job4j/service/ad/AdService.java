package ru.job4j.service.ad;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import ru.job4j.domain.ad.Ad;
import ru.job4j.repository.ad.AdDataRepository;
import ru.job4j.service.specification.AdSpecification;

import java.util.List;
import java.util.Map;
@Service
public class AdService {
    private final AdDataRepository adDataRepository;
    @Autowired
    public AdService(AdDataRepository adDataRepository) {
        this.adDataRepository = adDataRepository;
    }

    public int save(Ad ad) {
        int id = 0;
        if (ad != null) {
            adDataRepository.save(ad);
            id = ad.getId();
        }
        return id;
    }
    public void update(Ad ad) {
        if (ad != null) {
            adDataRepository.save(ad);
        }
    }
    public void delete(Ad ad) {
        if (ad != null) {
            adDataRepository.delete(ad);
        }
    }
    public List<Ad> getAds() {
        return adDataRepository.findAllByOrderByIdAsc();
    }

    public List<Ad> getAdsByUserId(int userId) {
        return adDataRepository.findAdsByUserIdOrderByIdAsc(userId);
    }

    public Ad getAdById(int id) {
        return adDataRepository.findAdById(id);
    }

    public List<Ad> getAdsByFilter(Map<String, String> filter) {
        Specification adSpecification = AdSpecification.orderById();
        for (String key : filter.keySet()) {
            String value = filter.get(key);
            if (key.equals("currentDay")) {
                adSpecification = adSpecification.and(AdSpecification.currentDay());
            }
            if (key.equals("withPhoto")) {
                adSpecification = adSpecification.and(AdSpecification.withPhoto());
            }
            if (key.equals("actual")) {
                adSpecification = adSpecification.and(AdSpecification.actual());
            }
            if (key.equals("byName")) {
                adSpecification = adSpecification.and(AdSpecification.byName(value));
            }
        }
        return (List<Ad>) adDataRepository.findAll(adSpecification);
    }
}
