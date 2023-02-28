package com.epam.learn.backendservice.config.datasource;

import javax.sql.DataSource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("QA")
public class DataSourceCustomConfig {

  @Bean
  @ConditionalOnMissingBean(DataSource.class)
  public DataSource dataSource() {
    DataSourceBuilder<?> dataSourceBuilder = DataSourceBuilder.create();
    dataSourceBuilder.url("jdbc:h2:mem:dev");
    dataSourceBuilder.username("dev");
    dataSourceBuilder.password("dev");

    return dataSourceBuilder.build();
  }
}
