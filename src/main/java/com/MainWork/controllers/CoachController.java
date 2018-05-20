package com.MainWork.controllers;

import com.MainWork.modules.users.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

@RestController
@RequestMapping("/coaches")
public class CoachController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/")
    public LinkedList<Coach> getAllCoaches () throws SQLException{
        Statement stm = dataSource.getConnection().createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM public.coach ");
        LinkedList<Coach> coaches = new LinkedList<>();
        while (rs.next()){
            coaches.add(new Coach(rs.getInt(1) , rs.getString(2), rs.getString(3), rs.getString(4)));
        }
        rs.close();
        stm.close();
        dataSource.getConnection().close();
        return coaches;
    }

    @RequestMapping("/getCoachById{id}")
    public Coach getStrudentById(@PathVariable(value="id") int id) throws SQLException{
        Statement stm = dataSource.getConnection().createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM public.coach WHERE coachid="+id);
        rs.next();
        Coach coach = new Coach(rs.getInt(1) , rs.getString(2), rs.getString(3), rs.getString(4));
        rs.close();
        stm.close();
        dataSource.getConnection().close();
        return coach;
    }
}
