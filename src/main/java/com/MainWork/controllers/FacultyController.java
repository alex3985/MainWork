package com.MainWork.controllers;

import com.MainWork.modules.faculty.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/")
    public Object getAllFaculties() throws SQLException{
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM public.\"Faculty\"");
        if(!rs.next()){
            return "error";
        }else{
            LinkedList<Faculty> faculties = new LinkedList<>();
            do{
                faculties.add(new Faculty(rs.getInt(1),rs.getString(2)));
            }while(rs.next());
            rs.close();
            stm.close();
            con.close();
            return faculties;
        }
    }
}
