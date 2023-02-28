package com.epam.learn.backendservice.config.datasource;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("DEV")
public class DataSourceAutoConfig {

  @Bean
  public DataSource dataSource() {
    DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.url("jdbc:h2:mem:test");
    dataSourceBuilder.username("test");
    dataSourceBuilder.password("test");

    return dataSourceBuilder.build();
  }
}
