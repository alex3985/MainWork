package com.MainWork.controllers;

import com.MainWork.modules.authorization.User;
import com.MainWork.modules.users.Coach;
import com.MainWork.modules.users.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/authorization/{login}/{password}")
    public Object Authorizathion (@PathVariable(value = "login") String login,@PathVariable(value = "password") String password) throws SQLException, ClassNotFoundException {
        PreparedStatement stmt = dataSource.getConnection().prepareStatement("SELECT * FROM public.users WHERE password="+"'"+password+"' AND login="+"'"+login+"'");
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
            User user =new User(rs.getInt(1),null,null,rs.getInt(4),rs.getInt(5));
            rs.close();
            stmt.close();
            dataSource.getConnection().close();
            return user;
        }
        rs.close();
        stmt.close();
        dataSource.getConnection().close();
        return "error";
    }

}
