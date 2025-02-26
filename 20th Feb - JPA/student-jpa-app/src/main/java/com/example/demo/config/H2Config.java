package com.example.demo.config;

import javax.sql.DataSource;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
    basePackages = "com.example.demo.repository.h2",
    entityManagerFactoryRef = "h2EntityManagerFactory",
    transactionManagerRef = "h2TransactionManager"
)
public class H2Config {

    @Value("${first.datasource.url}")
    private String url;

    @Value("${first.datasource.username}")
    private String username;

    @Value("${first.datasource.password}")
    private String password;

    @Value("${first.datasource.driver-class-name}")
    private String driverClassName;

    @Bean
    public DataSource h2DataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl(url);
        dataSource.setUsername(username);
        dataSource.setPassword(password);
        dataSource.setDriverClassName(driverClassName);
        return dataSource;
    }

    @Bean(name = "h2EntityManagerFactory")
    public LocalContainerEntityManagerFactoryBean h2EntityManagerFactory() {
        LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(h2DataSource());
        em.setPackagesToScan("com.example.demo.model");

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
        properties.put("hibernate.hbm2ddl.auto", "update");
        em.setJpaPropertyMap(properties);

        return em;
    }
    
    @Bean(name = "h2TransactionManager")
    @Primary
    public PlatformTransactionManager h2TransactionManager(EntityManagerFactory h2EntityManagerFactory) {
        return new JpaTransactionManager(h2EntityManagerFactory);
    }
}
