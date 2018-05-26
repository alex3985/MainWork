package com.MainWork.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/student/count/{id}")
    public Object getAttendanceById(@PathVariable(value="id") int id) throws SQLException {
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT attendance FROM public.journal WHERE studentid="+id);
        if(rs.next()){
            int count = rs.getInt(1);
            rs.close();
            stm.close();
            con.close();
            return count;
        }
        rs.close();
        stm.close();
        con.close();
        return "error";
    }

    @RequestMapping("/student/lastDate/{id}")
    public Object getLastDateById(@PathVariable(value="id") int id) throws SQLException {
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT lastdate FROM public.journal WHERE studentid="+id);
        if(rs.next()){
            String lastdate;
            lastdate = new String(rs.getString(1));
            rs.close();
            stm.close();
            con.close();
            return lastdate;
        }
        rs.close();
        stm.close();
        con.close();
        return "error";
    }

    @RequestMapping("/coach/update/{id}/{count}/{lastdate}")
    public Object updateAttendance(@PathVariable(value = "id") int id,@PathVariable(value = "count") int count,@PathVariable(value = "lastdate") String lastdate) throws SQLException {
        if (id < 0 || (count < 0 || count > 80) ||lastdate.equals("")||!lastdate.matches("[0-9]+-[0-9]+-[0-9]+")) {
            return "error";
        }else{
            Connection con = dataSource.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT update_attendance("+id+","+count+",'"+lastdate+"')");
            if(rs.next()) {
                String massage;
                massage = new String(rs.getString(1));
                rs.close();
                stm.close();
                con.close();
                return massage;
            }
            rs.close();
            stm.close();
            con.close();
            return "error";
        }
    }
}
