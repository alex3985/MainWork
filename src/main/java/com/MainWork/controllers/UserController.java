package com.MainWork.controllers;

import com.MainWork.modules.users.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.*;

@RestController
public class UserController {

    @Autowired
    private DataSource dataSource;


    @RequestMapping("/authorization")
    public Object Authorizathion () throws SQLException, ClassNotFoundException {
        Coach coach = null;
        Statement stmt = dataSource.getConnection().createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM public.\"Coach\"");
        while (rs.next()) {
            coach = new Coach(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
        }
        return coach;
    }

}
