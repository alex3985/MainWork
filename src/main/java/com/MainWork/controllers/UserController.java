package com.MainWork.controllers;

import com.MainWork.modules.authorization.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;
import java.sql.*;

@RestController
public class UserController {

    @RequestMapping("/authorization")
    public Object Authorizathion () throws SQLException, URISyntaxException, ClassNotFoundException {
        Coach coach = null;
        Class.forName("org.postgresql.Driver");
        Connection connection = getConnection();
        Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Coach\"");
        while (rs.next()) {
            coach = new Coach(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
        }
        return coach;
    }

    private static Connection getConnection() throws URISyntaxException, SQLException, ClassNotFoundException {
        String username = System.getenv("zokdoohqjgdgqh");
        String password = System.getenv("4cec923b9ae69cee7a9f41a9cec6afc51685d6324f57cf7d9ff7c596d0c1405b");
        String dbUrl = "jdbc:postgresql://postgres://zokdoohqjgdgqh:4cec923b9ae69cee7a9f41a9cec6afc51685d6324f57cf7d9ff7c596d0c1405b@ec2-54-247-89-189.eu-west-1.compute.amazonaws.com:5432/db7vd0hkpm8872";

        return DriverManager.getConnection(dbUrl, username, password);
    }
}
