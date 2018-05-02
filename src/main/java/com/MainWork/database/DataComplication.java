package com.MainWork.database;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataComplication {

    private static Connection getConnection() throws URISyntaxException, SQLException {
        URI dbUri = new URI(System.getenv("postgres://zokdoohqjgdgqh:4cec923b9ae69cee7a9f41a9cec6afc51685d6324f57cf7d9ff7c596d0c1405b@ec2-54-247-89-189." +
                "eu-west-1.compute.amazonaws.com:5432/db7vd0hkpm8872"));

        String username = dbUri.getUserInfo().split(":")[0];
        String password = dbUri.getUserInfo().split(":")[1];
        String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();

        return DriverManager.getConnection(dbUrl, username, password);
    }
}
