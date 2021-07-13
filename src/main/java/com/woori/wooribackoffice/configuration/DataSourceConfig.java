package com.woori.wooribackoffice.configuration;

import com.woori.wooribackoffice.repository.SelectMapper;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import java.util.Properties;

@EnableJpaRepositories(basePackages = {"com.woori.wooribackoffice.repository", "com.woori.wooribackoffice.security.repository"})   // for repository scan
@EnableTransactionManagement
@Configuration
@MapperScan(basePackageClasses = {SelectMapper.class})
// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#reference
public class DataSourceConfig {
    @Bean
    @ConfigurationProperties("spring.datasource.hikari")    // 가장 깔끔한 data source 설정 방법으로 생각됨
    HikariDataSource getDataSource() {
        return new HikariDataSource();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        vendorAdapter.setGenerateDdl(true);

        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        factory.setPackagesToScan("com.woori.wooribackoffice.domain.entity");   // for entity package scan
        factory.setDataSource(getDataSource());
        factory.setJpaProperties(additionalProperties());
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager(EntityManagerFactory entityManagerFactory) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(entityManagerFactory);
        return txManager;
    }

    /**
     * yml 파일에서 설정시 먹히지 않아서 이렇게 설정 시도
     */
    Properties additionalProperties() {
        /*
            "hibernate.hbm2ddl.auto","none",
            "hibernate.dialect", dialectName,
            "hibernate.open-in-view", "true",
            "hibernate.format_sql", "true"
         */
        Properties properties = new Properties();
        properties.setProperty("hibernate.hbm2ddl.auto", "validate");
        return properties;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(getDataSource());
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sessionFactory.setMapperLocations(resolver.getResource("classpath:/mapper/SelectMapper.xml"));

        org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
        config.setMapUnderscoreToCamelCase(true);
        config.setAutoMappingBehavior(AutoMappingBehavior.PARTIAL);
        config.setJdbcTypeForNull(JdbcType.NULL);
//        config.getTypeAliasRegistry().registerAliases("com.app.entity");
        sessionFactory.setConfiguration(config);

        return sessionFactory.getObject();
    }
}
