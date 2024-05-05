package com.todo.todo.run;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:postgresql://aws-0-ap-south-1.pooler.supabase.com:5432/postgres");
        dataSource.setUsername("postgres.vivqbzffgkuthpxcqetp");
        dataSource.setPassword("zaGguYHmiNS@&8V");
        return dataSource;
    }
}
