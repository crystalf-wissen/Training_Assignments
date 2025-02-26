package com.example.demo.config;

import javax.sql.DataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableJpaRepositories(
    basePackages = "com.example.demo.repository.postgres",
    entityManagerFactoryRef = "postgresEntityManagerFactory",
    transactionManagerRef = "postgresTransactionManager"
)
public class PostgresConfig {

    @Value("${second.datasource.url}")
    private String url;

    @Value("${second.datasource.username}")
    private String username;

    @Value("${second.datasource.password}")
    private String password;

    @Value("${second.datasource.driver-class-name}")
    private String driverClassName;

    @Bean
    public DataSource postgresDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    @Bean(name = "postgresEntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean postgresEntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(postgresDataSource());
        em.setPackagesToScan("com.example.demo.model");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.PostgreSQLDialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean(name = "postgresTransactionManager")
    public PlatformTransactionManager postgresTransactionManager(EntityManagerFactory postgresEntityManagerFactory) {
        return new JpaTransactionManager(postgresEntityManagerFactory);
    }
}
