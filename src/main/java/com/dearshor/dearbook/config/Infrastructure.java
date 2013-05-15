package com.dearshor.dearbook.config;

import static org.springframework.orm.jpa.vendor.Database.HSQL;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseFactoryBean;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration @EnableTransactionManagement 
public class Infrastructure {
	
	@Bean
	public EntityManagerFactory entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		emf.setDataSource(dataSource());
		HibernateJpaVendorAdapter jpaVendorAdapter = new HibernateJpaVendorAdapter();
		jpaVendorAdapter.setDatabase(HSQL);
		jpaVendorAdapter.setGenerateDdl(true);
		jpaVendorAdapter.setShowSql(true);
		emf.setJpaVendorAdapter(jpaVendorAdapter);
		emf.setPersistenceUnitName("com.dearshor.dearsbook.persistence-unit.dev");
		emf.afterPropertiesSet();
		return emf.getObject();
	}
	
	@Bean
	public DataSource dataSource() {
		EmbeddedDatabaseFactoryBean dbf = new EmbeddedDatabaseFactoryBean();
		dbf.setDatabaseType(EmbeddedDatabaseType.HSQL);
		dbf.afterPropertiesSet();
		return dbf.getObject();
	}

	@Bean
	public JpaTransactionManager transactionManager() {
		JpaTransactionManager jpaTransactionManager = new JpaTransactionManager(entityManagerFactory());
		return jpaTransactionManager;
		
	}
	
	/**
	 * <p>While configing a bean of type {@link javax.persistence.EntityManagerFactory}, it's necessary for creating {@link org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor}, 
	 * otherwise it cause {@link java.lang.IllegalStateException}: No persistence exception translators found in bean factory. Cannot perform exception translation.</p>
	 * 
	 * <p>Oppositely, for instance, while configing a bean of type LocalContainerEntityManagerFactoryBean, it's unnecessary. why?</p>
	 * 
	 * @return instance of {@link org.springframework.orm.hibernate4.HibernateExceptionTranslator}
	 */
	@Bean 
    public HibernateExceptionTranslator hibernateExceptionTranslator(){ 
      return new HibernateExceptionTranslator(); 
    }

}
