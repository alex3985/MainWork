package com.MainWork.controllers;

import com.MainWork.modules.StudentForTableCoach;
import com.MainWork.modules.users.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
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
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM public.coach ");
        LinkedList<Coach> coaches = new LinkedList<>();
        while (rs.next()){
            coaches.add(new Coach(rs.getInt(1) , rs.getString(2), rs.getString(3), rs.getString(4)));
        }
        rs.close();
        stm.close();
        con.close();
        return coaches;
    }

    @RequestMapping("/getCoachById{id}")
    public Coach getStrudentById(@PathVariable(value="id") int id) throws SQLException{
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM public.coach WHERE coachid="+id);
        rs.next();
        Coach coach = new Coach(rs.getInt(1) , rs.getString(2), rs.getString(3), rs.getString(4));
        rs.close();
        stm.close();
        con.close();
        return coach;
    }

    @RequestMapping("/students/{id}")
    public Object getCoachesSrudentsById(@PathVariable(value = "id") int id) throws SQLException{
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT studentid,journal.facultyid,surname,student.name,student.patronymic,public.\"Faculty\".name,student.group," +
                "attendance,lastdate,student.phone,student.sex FROM journal INNER JOIN (public.student INNER JOIN public.\"Faculty\"" +
                " ON student.facultyid=public.\"Faculty\".facultyid) ON journal.sectionid=student.studentid WHERE coachid="+id);
        if(!rs.next()){
           rs.close();
           stm.close();
           con.close();
           return "error";
        }else{
            LinkedList<StudentForTableCoach> stud =new LinkedList<>();
            do{
                stud.add(new StudentForTableCoach(rs.getInt(1),rs.getInt(2),rs.getString(3)+" "+rs.getString(4)+" "+
                rs.getString(5),rs.getNString(6),rs.getString(7),rs.getInt(8),rs.getString(9),rs.getString(10),rs.getString(11)));
            }while (rs.next());
            rs.close();
            stm.close();
            con.close();
            return stud;
        }
    }
}
