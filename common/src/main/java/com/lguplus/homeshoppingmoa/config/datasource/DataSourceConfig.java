package com.lguplus.homeshoppingmoa.config.datasource;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
		basePackages = { "com.lguplus.homeshoppingmoa" }
)
@EntityScan(
		basePackages = { "com.lguplus.homeshoppingmoa" }
)
public class DataSourceConfig {

}
