package com.MainWork.controllers;

import com.MainWork.modules.exam.ResultOfExam;
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
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/student/{id}")
    public Object getExamsById(@PathVariable(value = "id") int id) throws SQLException {
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT name,measure,five,two,three,four,data FROM public.standards INNER JOIN " +
                "public.exam ON public.standards.standardid=public.exam.standardid WHERE public.exam.studentid="+id);
        if(!rs.next()){
            rs.close();
            stm.close();
            con.close();
            return "error";
        }else{
            LinkedList<ResultOfExam> exam = new LinkedList<>();
            do{
                exam.add(new ResultOfExam(rs.getString(1),rs.getString(2),rs.getDouble(7),rs.getDouble(4),
                        rs.getDouble(5),rs.getDouble(6),rs.getDouble(3)));
            }while (rs.next());
            rs.close();
            stm.close();
            con.close();
            return exam;
        }
    }

    @RequestMapping("/coach/insert/{studentid}/{standardid}/{data}")
    public String insertExam(@PathVariable(value = "studentid") int studentid,@PathVariable(value = "standardid") int standardid,
                             @PathVariable(value = "data") double data) throws SQLException {
        if (studentid < 0||standardid<0||data<0) {
            return "error";
        }else{
            Connection con = dataSource.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT insert_exam("+studentid+","+standardid+","+data+")");
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

    @RequestMapping("/coach/update/{studentid}/{standardid}/{data}")
    public String updateExam(@PathVariable(value = "studentid") int studentid,@PathVariable(value = "standardid") int standardid,
                             @PathVariable(value = "data") double data) throws SQLException {
        if (studentid < 0||standardid<0||data<0) {
            return "error";
        }else{
            Connection con = dataSource.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT update_exam("+studentid+","+standardid+","+data+")");
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

    @RequestMapping("/coach/delete/{studentid}/{standardid}")
    public String deleteExam(@PathVariable(value = "studentid") int studentid,@PathVariable(value = "standardid") int standardid) throws SQLException {
        if (studentid < 0||standardid<0) {
            return "error";
        }else{
            Connection con = dataSource.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT delete_exam("+studentid+","+standardid+")");
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
