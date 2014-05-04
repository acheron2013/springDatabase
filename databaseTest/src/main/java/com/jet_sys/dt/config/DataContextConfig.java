package com.jet_sys.dt.config;

import javax.persistence.EntityManagerFactory;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableLoadTimeWeaving;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaDialect;
import org.springframework.orm.jpa.vendor.EclipseLinkJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@Profile("data")
@EnableJpaRepositories(basePackages="com.jet_sys.dt.repository") //, transactionManagerRef="transactionManager",entityManagerFactoryRef="entityManagerFactory")
@ComponentScan(basePackages= {"com.jet_sys.dt.model"
						,"com.jet_sys.dt.repository"})
@EnableTransactionManagement
@EnableLoadTimeWeaving
@ImportResource("classpath:/properties-config.xml")
public class DataContextConfig {
	@Value("#{dtProperties['com.jet_sys.dt.dataSource.Url']}")
	public String dataSourceUrl;
	@Value("#{dtProperties['com.jet_sys.dt.dataSource.Username']}")
	public String dataSourceUsername;
	@Value("#{dtProperties['com.jet_sys.dt.dataSource.Password']}")
	public String dataSourcePassword;
	@Value("#{dtProperties['com.jet_sys.dt.persistenceUnitName']}")
	public String persistenceUnitName;

	@Autowired
	EntityManagerFactory entityManagerFactory;

	@Bean()
	public DriverManagerDataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("org.springframework.jdbc.datasource.DriverManagerDataSource");
		dataSource.setUrl(dataSourceUrl);
		dataSource.setUsername(dataSourceUsername);
		dataSource.setPassword(dataSourcePassword);
		return dataSource;
	}

	@Bean()
	EclipseLinkJpaVendorAdapter eclipseLinkJpaVendorAdapter() {
		EclipseLinkJpaVendorAdapter eclipseLinkJpaVendorAdapter = new EclipseLinkJpaVendorAdapter();
		eclipseLinkJpaVendorAdapter.setShowSql(true);
		eclipseLinkJpaVendorAdapter.setGenerateDdl(false);
		eclipseLinkJpaVendorAdapter.setDatabasePlatform("org.eclipse.persistence.platform.database.PostgreSQLPlatform");
		return eclipseLinkJpaVendorAdapter;
	}

	@Bean()
	EclipseLinkJpaDialect jpaDialect() {
		EclipseLinkJpaDialect jpaDialect = new EclipseLinkJpaDialect();
		return jpaDialect;
	}

@Bean()
	public FactoryBean<EntityManagerFactory> entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean emf = new LocalContainerEntityManagerFactoryBean();
		//emf.setPackagesToScan("com.gxs.ieexb.jpa");
		emf.setPackagesToScan(new String[] { "com.jet_sys.dt.model" });
		emf.setPersistenceUnitName(persistenceUnitName);
		emf.setDataSource(dataSource());
		//emf.setLoadTimeWeaver(loadTimeWeaver());
		emf.setJpaDialect(new EclipseLinkJpaDialect());
		emf.setJpaVendorAdapter(eclipseLinkJpaVendorAdapter());
		//emf.setPersistenceProvider(persistenceProvider());
		return emf;
	}

@Bean
public PlatformTransactionManager transactionManager() throws Exception {

  JpaTransactionManager txManager = new JpaTransactionManager();
  txManager.setEntityManagerFactory(entityManagerFactory);
  return txManager;
}


//	@Bean()
//	public LoadTimeWeaver loadTimeWeaver() {
//		LoadTimeWeaver loadTimeWeaver = new ReflectiveLoadTimeWeaver();
//		return loadTimeWeaver;
//	}

}