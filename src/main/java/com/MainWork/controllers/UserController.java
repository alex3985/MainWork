package com.MainWork.controllers;

import com.MainWork.modules.users.Coach;
import com.MainWork.modules.users.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
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
        PreparedStatement stmt = dataSource.getConnection().prepareStatement("SELECT * FROM public.\"Users\" WHERE password="+"'{"+password+"}'"+" AND login="+"'{"+login+"}'");
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
            Integer coachId = rs.getInt(4);
            Integer studentId = rs.getInt(5);
            System.out.println("esfdg "+coachId);
            System.out.println("esfdg "+studentId);
            if(coachId>0&&studentId==0) {
               PreparedStatement stmt2 = dataSource.getConnection().prepareStatement("SELECT * FROM public.\"Coach\" WHERE coachid=?");
               stmt2.setInt(1,rs.getInt(4));
               ResultSet rs2= stmt2.executeQuery();
               rs2.next();
               Coach coach = new Coach(rs2.getInt(1) , rs2.getString(2), rs2.getString(3), rs2.getString(4));
               stmt2.close();
               stmt.close();
               return coach;
            }else{
                PreparedStatement stmt2 = dataSource.getConnection().prepareStatement("SELECT * FROM public.\"Student\" WHERE studentid=?");
                stmt2.setInt(1,rs.getInt(5));
                ResultSet rs2= stmt2.executeQuery();
                rs2.next();
                Student student = new Student(rs2.getInt(1) , rs2.getString(2), rs2.getString(3), rs2.getString(4),
                        rs2.getString(5),rs2.getString(6),rs2.getString(7),rs2.getInt(8));
                stmt2.close();
                stmt.close();
                return student;
            }
        }return null;

    }

}
