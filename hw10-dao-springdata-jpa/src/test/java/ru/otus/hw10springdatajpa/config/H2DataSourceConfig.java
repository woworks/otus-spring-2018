package ru.otus.hw10springdatajpa.config;

import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

//@Configuration
public class H2DataSourceConfig {

    //@Bean
    public DataSource dataSource() {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");
        dataSource.setUsername("sa");
        dataSource.setPassword("");
        dataSource.setDriverClassName("org.h2.Driver");
        return dataSource;
    }
}
