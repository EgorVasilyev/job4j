package ru.job4j.service.user;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.dao.user.UserDaoImpl;
import ru.job4j.entity.ad.Ad;
import ru.job4j.entity.car.*;
import ru.job4j.entity.user.User;
import ru.job4j.service.ad.AdService;
import ru.job4j.service.car.*;

import java.util.List;

public class UserService {
    private static final Logger LOG = LogManager.getLogger(UserService.class.getName());
    private static final UserDaoImpl USER_DAO = UserDaoImpl.getInstance();
    private static final UserService INSTANCE = new UserService();

    public UserService() {
    }

    public static UserService getInstance() {
        return INSTANCE;
    }
    public int save(User user) {
        int id = 0;
        if (user != null) {
             id = USER_DAO.save(user);
        }
        return id;
    }
    public void update(User user) {
        if (user != null) {
            USER_DAO.update(user);
        }
    }
    public void delete(User user) {
        if (user != null) {
            USER_DAO.delete(user);
        }
    }
    public User isCredential(String login, String password) {
        return this.getUsers().stream()
                .filter(user -> user.getLogin().equals(login) && user.getPassword().equals(password))
                .findFirst()
                .orElse(null);
    }
    
    public boolean contains(String login) {
        return this.getUsers().stream().anyMatch(user -> user.getLogin().equals(login));
    }

    public List<User> getUsers() {
        return USER_DAO.getEntities();
    }

    public User getUserById(int id) {
        return USER_DAO.getUserById(id);
    }

    public static void main(String[] args) {
        Color color = new Color();
        color.setName("colorTest");
        System.out.println(color);
        color.setId(new ColorService().save(color));

        Engine engine = new Engine();
        engine.setName("engineTest");
        engine.setId(new EngineService().save(engine));

        Year year = new Year();
        year.setValue(1993);
        year.setId(new YearService().save(year));

        Body body = new Body();
        body.setName("bodyTest");
        body.setId(new BodyService().save(body));

        Car car = new Car();
        car.setName("carTest");
        car.setBody(body);
        car.setColor(color);
        car.setEngine(engine);
        car.setYear(year);
        car.setId(new CarService().save(car));

        User user = new User();
        user.setLogin("testLogin");
        user.setPassword("testPass");
        user.setPhone("testPhone");
        user.setRole("testRole");
        user.setId(new UserService().save(user));

        Ad ad = new Ad();
        ad.setDescription("testDesc");
        ad.setCar(car);
        ad.setUser(user);
        ad.setClosed(false);
       // ad.setPicture("testUrl");
        ad.setId(new AdService().save(ad));

        System.out.println("______________________________");
        System.out.println(new AdService().getAds());
        System.out.println(new UserService().getUsers());
        System.out.println(new CarService().getCars());
        System.out.println(new ColorService().getColors());
        System.out.println(new BodyService().getBodies());
        System.out.println(new YearService().getYears());
        System.out.println(new EngineService().getEngines());
        System.out.println("______________________________");

    }
}
