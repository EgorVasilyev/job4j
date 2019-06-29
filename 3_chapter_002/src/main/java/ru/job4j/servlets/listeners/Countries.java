package ru.job4j.servlets.listeners;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.servlets.datamodel.City;
import ru.job4j.servlets.datamodel.Country;
import ru.job4j.servlets.persistent.DbStore;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Collectors;

public class Countries implements ServletContextListener {
    private static final Logger LOG = LogManager.getLogger(Countries.class.getName());
    private static final BasicDataSource SOURCE = new BasicDataSource();
    private Properties properties;

    public Countries() {
        LOG.info("Constructor Countries()");
        try (InputStream in = DbStore.class.getClassLoader().getResourceAsStream("db.properties")) {
            this.properties = new Properties();
            this.properties.load(in);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        SOURCE.setDriverClassName(this.properties.getProperty("DriverClassName"));
        SOURCE.setUrl(this.properties.getProperty("path"));
        SOURCE.setUsername(this.properties.getProperty("username"));
        SOURCE.setPassword(this.properties.getProperty("password"));
        SOURCE.setMinIdle(Integer.valueOf(this.properties.getProperty("minIdle")));
        SOURCE.setMaxIdle(Integer.valueOf(this.properties.getProperty("maxIdl")));
        SOURCE.setMaxOpenPreparedStatements(Integer.valueOf(this.properties.getProperty("MaxOpenPreparedStatements")));
        this.doQuery("Try to create countries table", "createCountriesTable");
        this.doQuery("Try to create cities table", "createCitiesTable");
        this.doQuery("Try to fill countries table", "fillCountriesTable");
        this.doQuery("Try to fill cities table", "fillCitiesTable");
    }

    private void doQuery(String logInfo, String queryName) {
        LOG.info(logInfo);
        try (Connection connection = SOURCE.getConnection();
             Statement st = connection.createStatement()) {
            st.execute(this.properties.getProperty(queryName));
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public ConcurrentHashMap<Integer, Country> findAllCountries() {
        LOG.info("find all countries");
        ConcurrentHashMap<Integer, Country> countries = new ConcurrentHashMap<>();
        try (Connection connection = SOURCE.getConnection();
             Statement stFindAll = connection.createStatement()) {
            ResultSet result = stFindAll.executeQuery(this.properties.getProperty("queryAllCountries"));
            countries = this.findCountries(result);
            result.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return countries;
    }
    private ConcurrentHashMap<Integer, Country> findCountries(ResultSet result) {
        ConcurrentHashMap<Integer, Country> countries = new ConcurrentHashMap<>();
        try {
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                countries.put(id, new Country(id, name, findCitiesByCountryId(id)));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return countries;
    }
    private CopyOnWriteArrayList<City> findCitiesByCountryId(int id) {
        LOG.info("find all cities by country id = " + id);
        CopyOnWriteArrayList<City> cities = new CopyOnWriteArrayList<>();
        try (Connection connection = SOURCE.getConnection(); PreparedStatement stFindAll =
                connection.prepareStatement(this.properties.getProperty("queryAllCitiesByCountryId"))
        ) {
            stFindAll.setInt(1, id);
            ResultSet result = stFindAll.executeQuery();
            cities = this.findCities(result);
            result.close();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return cities;
    }
    private CopyOnWriteArrayList<City> findCities(ResultSet result) {
        CopyOnWriteArrayList<City> cities = new CopyOnWriteArrayList<>();
        try {
            while (result.next()) {
                int id = result.getInt("id");
                String name = result.getString("name");
                cities.add(new City(id, name));
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return cities;
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext sc = sce.getServletContext();
        ConcurrentHashMap<Integer, Country> countries = findAllCountries();
        CopyOnWriteArrayList<City> cities = countries.values().stream()
                .flatMap(country -> country.getCities().stream())
                .collect(Collectors.toCollection(CopyOnWriteArrayList::new));
        sc.setAttribute("countriesMap", countries);
        sc.setAttribute("citiesList", cities);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
