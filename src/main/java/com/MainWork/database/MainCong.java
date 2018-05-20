package com.MainWork.database;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.MainWork.database")
public class MainCong{

    @Bean
    public static BasicDataSource dataSource() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String username = "icggyrbzbeuocq";
        String password = "2c6b5d661e938f43320bfe20a805ff94be5509cb0b9e7cf2f41e467163ff7689";
        String dbUrl = "jdbc:postgresql://ec2-54-75-239-237.eu-west-1.compute.amazonaws.com:5432/dfgqskbforn93?sslmode=require";

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);
        basicDataSource.setMaxIdle(0);
        basicDataSource.setTimeBetweenEvictionRunsMillis(5);
        return basicDataSource;
    }

}
