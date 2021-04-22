package com.elevator.ElevatorSpringBootApplication;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class PostgresConfig {

    @Bean
    public DataSource postgresDataSource() {
        final HikariDataSource dataSource = new HikariDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        // change localhost to aws RDS instance URL
        dataSource.setJdbcUrl("jdbc:postgresql://localhost:5432/elevator");
        dataSource.setUsername("elevator");
        dataSource.setPassword("password");
        dataSource.setSchema("elevator_schema");
        return dataSource;
    }
}
