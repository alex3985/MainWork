package com.MainWork.controllers;

import com.MainWork.modules.day.Day;
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
@RequestMapping("/day")
public class DayController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/")
    public Object getDaies() throws SQLException {
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM public.day");
        if(!rs.next()){
            rs.close();
            stm.close();
            con.close();
            return "error";
        }else{
            LinkedList<Day> daies = new LinkedList<>();
            do{
                daies.add(new Day(rs.getInt(1),rs.getString(2)));
            }while(rs.next());
            rs.close();
            stm.close();
            con.close();
            return daies;
        }
    }
}
