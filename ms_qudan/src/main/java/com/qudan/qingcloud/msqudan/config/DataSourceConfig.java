package com.qudan.qingcloud.msqudan.config;

import com.github.pagehelper.PageHelper;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.Filter;
import java.util.Properties;


@Configuration
@EnableTransactionManagement
@MapperScan({"com.qudan.qingcloud.msqudan.mymapper"})
public class DataSourceConfig {

    @Bean
    @Primary
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource masterDataSource() {
        DataSource dataSource = new DataSource();
        return dataSource;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(masterDataSource());
        factory.setConfigLocation(new ClassPathResource("/mybatis-config.xml"));
        return factory;
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(masterDataSource());
    }

    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        return characterEncodingFilter;
    }
}
