package com.MainWork.database;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.MainWork.database")
public class MainCong{

    @Bean
    public BasicDataSource dataSource() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        String username = "zokdoohqjgdgqh";
        String password = "4cec923b9ae69cee7a9f41a9cec6afc51685d6324f57cf7d9ff7c596d0c1405b";
        String dbUrl = "jdbc:postgresql://ec2-54-247-89-189.eu-west-1.compute.amazonaws.com:5432/db7vd0hkpm8872?sslmode=require";

        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setUrl(dbUrl);
        basicDataSource.setUsername(username);
        basicDataSource.setPassword(password);

        return basicDataSource;
    }

}
