package com.donegel.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

/**
 *
 * @author erdigurbuz
 */
@Configuration
public class MultipleDataSourceConfiguration {

    @Bean
    @Primary

    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource primaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "maximoDb")
    @ConfigurationProperties(prefix = "spring.seconddatasource")
    public DataSource secondaryDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "maximoDbTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("maximoDb") DataSource dsMaximo) {
        return new JdbcTemplate(dsMaximo);
    }
}
