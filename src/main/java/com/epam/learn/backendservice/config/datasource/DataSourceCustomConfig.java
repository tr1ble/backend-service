package com.epam.learn.backendservice.config.datasource;

import javax.sql.DataSource;
import lombok.AllArgsConstructor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.jdbc.DataSourceProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@AllArgsConstructor
@Profile("QA")
public class DataSourceCustomConfig {
  private DataSourceProperties dataSourceProperties;

  @Bean
  @ConditionalOnMissingBean(DataSource.class)
  public DataSource dataSource() {
    DataSourceBuilder<?> dataSourceBuilder = dataSourceProperties.initializeDataSourceBuilder();

    return dataSourceBuilder.build();
  }
}
