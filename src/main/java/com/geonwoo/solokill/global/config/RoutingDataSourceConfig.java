package com.geonwoo.solokill.global.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.hibernate.SpringImplicitNamingStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.geonwoo.solokill.global.db.RoutingDataSource;
import com.zaxxer.hikari.HikariDataSource;

import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;

@Configuration
@EnableTransactionManagement
@RequiredArgsConstructor
@Profile("!test")
public class RoutingDataSourceConfig {

	private final Environment env;

	@ConfigurationProperties(prefix = "spring.datasource.hikari.master")
	@Bean
	public DataSource masterDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@ConfigurationProperties(prefix = "springdatasource.hikari.slave")
	@Bean
	public DataSource slaveDataSource() {
		return DataSourceBuilder.create().type(HikariDataSource.class).build();
	}

	@DependsOn({"masterDataSource", "slaveDataSource"})
	@Bean
	public DataSource routingDataSource(
		@Qualifier("masterDataSource") DataSource master,
		@Qualifier("slaveDataSource") DataSource slave) {
		RoutingDataSource routingDataSource = new RoutingDataSource();

		Map<Object, Object> dataSourceMap = new HashMap<>();

		dataSourceMap.put("master", master);
		dataSourceMap.put("slave", slave);

		routingDataSource.setTargetDataSources(dataSourceMap);
		routingDataSource.setDefaultTargetDataSource(master);

		return routingDataSource;
	}

	@DependsOn({"routingDataSource"})
	@Bean
	public DataSource dataSource(@Qualifier("routingDataSource") DataSource dataSource) {
		return new LazyConnectionDataSourceProxy(dataSource);
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(
		@Qualifier("dataSource") DataSource dataSource) {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(dataSource);
		em.setPackagesToScan("com.geonwoo.solokill.domain");
		em.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.physical_naming_strategy",
			"org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy");
		properties.put("hibernate.implicit_naming_strategy",
			SpringImplicitNamingStrategy.class.getName());
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("spring.jpa.hibernate.ddl-auto", "create"));
		properties.put("hibernate.show_sql", env.getProperty("jpa.show-sql", "false"));
		properties.put("hibernate.format_sql", env.getProperty("jpa.hibernate.format_sql", "false"));
		properties.put("hibernate.order_inserts", env.getProperty("jpa.hibernate.order_inserts", "true"));
		properties.put("hibernate.jdbc.batch_size", env.getProperty("jpa.hibernate.jdbc.batch_size", "100"));
		properties.put("hibernate.dialect",
			env.getProperty("jpa.hibernate.dialect", "org.hibernate.dialect.MySQLDialect"));
		em.setJpaPropertyMap(properties);
		return em;
	}

	@Primary
	@Bean
	public PlatformTransactionManager transactionManager(
		EntityManagerFactory entityManagerFactory) {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(entityManagerFactory);

		return transactionManager;
	}

}
