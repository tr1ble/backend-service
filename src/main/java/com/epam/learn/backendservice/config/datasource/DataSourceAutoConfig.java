package com.epam.learn.backendservice.config.datasource;

import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("DEV")
public class DataSourceAutoConfig {

  private static final String URL = "jdbc:h2:mem:dev";
  private static final String USERNAME = "dev";
  private static final String PASSWORD = "dev";

  @Bean
  public DataSource dataSource() {
    DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.url(URL);
    dataSourceBuilder.username(USERNAME);
    dataSourceBuilder.password(PASSWORD);

    return dataSourceBuilder.build();
  }
}
