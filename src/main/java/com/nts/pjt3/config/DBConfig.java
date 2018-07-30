package com.nts.pjt3.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

@Configuration
@EnableTransactionManagement
public class DBConfig implements TransactionManagementConfigurer {

	@Bean
	public DataSource dataSource() {
		InputStream inputStream = getClass().getResourceAsStream("/application.properties");
		Properties props = new Properties();

		BasicDataSource dataSource = new BasicDataSource();
		String driverClassName, url, username, password;
		try {
			props.load(inputStream);
			driverClassName = props.getProperty("spring.datasource.driver-class-name");
			url = props.getProperty("spring.datasource.url");
			username = props.getProperty("spring.datasource.username");
			password = props.getProperty("spring.datasource.password");
			inputStream.close();
			
			Class.forName("com.mysql.jdbc.Driver");

			dataSource.setDriverClassName(driverClassName);
			dataSource.setUrl(url);
			dataSource.setUsername(username);
			dataSource.setPassword(password);
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}

		return dataSource;
	}

	@Override
	public PlatformTransactionManager annotationDrivenTransactionManager() {
		return transactionManger();
	}

	@Bean
	public PlatformTransactionManager transactionManger() {
		return new DataSourceTransactionManager(dataSource());
	}
}