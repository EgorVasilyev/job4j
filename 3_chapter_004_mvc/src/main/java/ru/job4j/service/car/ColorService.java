package ru.job4j.service.car;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.car.Color;
import ru.job4j.repository.car.ColorDataRepository;

import java.util.List;
@Service
public class ColorService {
    private final ColorDataRepository colorDataRepository;
    @Autowired
    public ColorService(ColorDataRepository colorDataRepository) {
        this.colorDataRepository = colorDataRepository;
    }

    public int save(Color color) {
        int id = 0;
        if (color != null) {
            colorDataRepository.save(color);
            id = color.getId();
        }
        return id;
    }
    public void update(Color color) {
        if (color != null) {
            colorDataRepository.save(color);
        }
    }
    public void delete(Color color) {
        if (color != null) {
            colorDataRepository.delete(color);
        }
    }
    public List<Color> getColors() {
        return colorDataRepository.findAllByOrderByIdAsc();
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
