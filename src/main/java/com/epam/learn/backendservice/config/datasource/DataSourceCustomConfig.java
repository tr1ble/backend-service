package com.epam.learn.backendservice.config.datasource;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
public class DataSourceCustomConfig {
  private static final String URL = "jdbc:h2:mem:qa";
  private static final String USERNAME = "qa";
  private static final String PASSWORD = "qa";

  @Bean
  @ConditionalOnMissingBean(DataSource.class)
  public DataSource dataSource() {
    DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.url(URL);
    dataSourceBuilder.username(USERNAME);
    dataSourceBuilder.password(PASSWORD);

    return dataSourceBuilder.build();
  }
}
