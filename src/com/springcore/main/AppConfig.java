package com.springcore.main;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
@ComponentScan(basePackages = "com.springcore.main")
public class AppConfig {
	
	static {
		System.out.println("Application Context Loaded");
	}
	
	@Bean
	public DataSource getDataSource() {
		String url = "jdbc:mysql://localhost:3306/ecom";
		String userDB = "root";
		String passDB = "Shardul@297";
		String driver = "com.mysql.cj.jdbc.Driver";
		
		DriverManagerDataSource dataSource = new DriverManagerDataSource(url, userDB, passDB);
		dataSource.setDriverClassName(driver);
		
		
		return dataSource;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource) {
		return new JdbcTemplate(dataSource);
	}
	
	
}
