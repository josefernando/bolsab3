package br.com.recatalog.bolsab3.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef =  "b3EntityManagerFactory",
                       basePackages = {"br.com.recatalog.bolsab3.model.domain.repository"}, transactionManagerRef = "b3TransactionManager")

public class DataSourceConfig {

	@Bean(name = "b3DataSource")
	@ConfigurationProperties(prefix = "spring.b3.datasource")
	public DataSource datasource() {
		return DataSourceBuilder.create().build();
	}
	
	@Bean(name="b3EntityManagerFactory")
	public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean(EntityManagerFactoryBuilder builder
				, @Qualifier("b3DataSource") DataSource dataSource ) {
		
		Map<String,Object> properties = new HashMap<>();
		properties.put("hibernate.ddl-auto", "none");
		properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL8Dialect");
		
		return builder.dataSource(dataSource).properties(properties).packages("br.com.recatalog.bolsab3.model").persistenceUnit("b3").build();
	}
	
	@Bean(name="b3TransactionManager")
	public PlatformTransactionManager transactionManager(@Qualifier("b3EntityManagerFactory") EntityManagerFactory entityManagerFactory) {
		return new JpaTransactionManager(entityManagerFactory);
	}	
}