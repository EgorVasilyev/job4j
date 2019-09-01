/*
package ru.job4j.service;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.entity.annotations.Car;
import ru.job4j.entity.annotations.Engine;
import ru.job4j.persistent.DbProvider;
import ru.job4j.persistent.SessionFactoryRollback;

import java.lang.reflect.Field;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.hasItems;

public class ServiceTest {

    @Before
    public void useReflection() throws NoSuchFieldException, IllegalAccessException {
        DbProvider provider = DbProvider.getInstance();
        Field field = provider.getClass().getDeclaredField("sessionFactory");
        field.setAccessible(true);
        SessionFactory factory = new Configuration().configure().buildSessionFactory();
        field.set(provider, SessionFactoryRollback.createSessionFactory(factory));
    }

    @Test
    public void whenSaveCarThenGetCarsReturnIt() {
        CarService carService = new CarService();
        Car car = new Car();
        car.setName("Lamborghini");
        carService.save(car);

        List<Car> cars = carService.getCars();

        assertThat(cars, hasItems(car));
        assertThat(cars.size(), is(1));
    }

    @Test
    public void whenUpdateCarThenGetCarsReturnIt() {
        EngineService engineService = new EngineService();
        Engine engine = new Engine();
        engine.setName("testEngine");
        engineService.save(engine);

        CarService carService = new CarService();
        Car car = new Car();
        car.setName("Lamborghini");
        carService.save(car);

        car.setEngine(engine);
        carService.update(car);

        List<Car> cars = carService.getCars();

        assertThat(cars, hasItems(car));
    }

    @Test
    public void whenDeleteCarThenGetCarsReturnNull() {
        CarService carService = new CarService();
        Car car = new Car();
        car.setName("Lamborghini");

        carService.save(car);
        carService.delete(car);

        List<Car> cars = carService.getCars();

        assertThat(cars.size(), is(0));
    }

    @After
    public void close() {
        DbProvider.getInstance().close();
    }
}
*/
