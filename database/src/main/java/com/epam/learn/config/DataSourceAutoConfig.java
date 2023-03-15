package com.epam.learn.config;

import javax.sql.DataSource;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("DEV")
@AllArgsConstructor
public class DataSourceAutoConfig {

  private DataSourceProperties dataSourceProperties;

  @Bean
  public DataSource dataSource() {
    DataSourceBuilder<?> dataSourceBuilder = dataSourceProperties.initializeDataSourceBuilder();

    return dataSourceBuilder.build();
  }
}
