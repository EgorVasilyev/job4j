package ru.job4j.service.car;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.dao.car.ColorDaoImpl;
import ru.job4j.entity.car.Color;

import java.util.List;
@Service
public class ColorService {
    private static final Logger LOG = LogManager.getLogger(ColorService.class.getName());
    private final ColorDaoImpl COLOR_DAO;
    @Autowired
    public ColorService(ColorDaoImpl color_dao) {
        COLOR_DAO = color_dao;
    }

    public int save(Color color) {
        int id = 0;
        if (color != null) {
            id = COLOR_DAO.save(color);
        }
        return id;
    }
    public void update(Color color) {
        if (color != null) {
            COLOR_DAO.update(color);
        }
    }
    public void delete(Color color) {
        if (color != null) {
            COLOR_DAO.delete(color);
        }
    }
    public List<Color> getColors() {
        return COLOR_DAO.getEntities();
    }
    public boolean contains(String name) {
        return this.getColors().stream().anyMatch(colorHas -> colorHas.getName().toLowerCase().equals(name.toLowerCase()));
    }
    public Color getByName(String name) {
        return this.getColors()
                .stream()
                .filter(color -> color.getName().toLowerCase().equals(name.toLowerCase()))
                .findFirst()
                .orElse(null);
    }
}
