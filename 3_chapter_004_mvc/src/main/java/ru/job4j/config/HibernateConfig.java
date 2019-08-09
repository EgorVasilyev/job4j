package ru.job4j.config;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@PropertySource({ "classpath:database.properties" })
@ComponentScan({ "ru.job4j" })
@EnableTransactionManagement(proxyTargetClass = true)
public class HibernateConfig {
    @Value("${driver_class}")
    private String driver_class;
    @Value("${url}")
    private String url;
    @Value("${user_name}")
    private String user_name;
    @Value("${password}")
    private String password;
    @Value("${dialect}")
    private String dialect;
    @Value("${hbm2ddl.auto}")
    private String hbm2ddl_auto;
    @Value("${current_session_context_class}")
    private String current_session_context_class;
    @Value("${show_sql}")
    private String show_sql;
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(driver_class);
        dataSource.setUrl(url);
        dataSource.setUsername(user_name);
        dataSource.setPassword(password);
        return dataSource;
    }
    @Bean
    public LocalSessionFactoryBean sessionFactory() {
        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        sessionFactory.setPackagesToScan("ru.job4j");
        sessionFactory.setHibernateProperties(hibernateProperties());
        return sessionFactory;
    }
    Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", dialect);
        properties.put("hbm2ddl.auto", hbm2ddl_auto);
        properties.put("current_session_context_class", current_session_context_class);
        properties.put("show_sql", show_sql);
        return properties;
    }
    @Bean
    @Autowired
    public HibernateTransactionManager transactionManager(
            SessionFactory sessionFactory) {

        HibernateTransactionManager transactionManager
                = new HibernateTransactionManager();
        transactionManager.setSessionFactory(sessionFactory);

        return transactionManager;
    }
}
