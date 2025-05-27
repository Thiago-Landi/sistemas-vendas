package com.Thiago_landi.sistemas.vendas.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class DatabaseConfiguration {
	
	@Value("${spring.datasource.url}")
	String url;
	@Value("${spring.datasource.username}")
	String username;
	@Value("${spring.datasource.password}")
	String password;
	@Value("${spring.datasource.driver-class-name}")
	String driver;
	
	@Bean
	public DataSource hikariDataSource() {

		HikariConfig config = new HikariConfig();
		config.setUsername(username);
		config.setPassword(password);
		config.setDriverClassName(driver);
		config.setJdbcUrl(url);
		return new HikariDataSource(config);

	}
}
