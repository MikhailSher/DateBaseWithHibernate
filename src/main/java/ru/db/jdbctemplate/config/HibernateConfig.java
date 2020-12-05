package ru.db.jdbctemplate.config;

import org.hibernate.cfg.Configuration;
import ru.db.jdbctemplate.models.Car;
import ru.db.jdbctemplate.models.User;
import java.util.Properties;

/**
 * 25.11.2020
 * HibernateConfig
 *
 * @author Sherstobitov Mikhail
 * @version v1.0
 */

public class HibernateConfig {

    public static Configuration getProperty(Properties properties) {

        Configuration configuration = new Configuration();

        String dbUrl = properties.getProperty("db.url");
        String dbUsername = properties.getProperty("db.username");
        String dbPassword = properties.getProperty("db.password");
        String driverClassName = properties.getProperty("db.driverClassName");


        configuration.setProperty("hibernate.connection.url", dbUrl);
        configuration.setProperty("hibernate.connection.username", dbUsername);
        configuration.setProperty("hibernate.connection.password", dbPassword);
        configuration.setProperty("hibernate.connection.driver_class", driverClassName);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.PostgreSQL95Dialect");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.addResource("User.hbm.xml");
        configuration.addAnnotatedClass(Car.class);
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.show_sql", "true");

        return configuration;
    }
}
