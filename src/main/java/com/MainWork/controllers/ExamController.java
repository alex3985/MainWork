package com.MainWork.controllers;

import com.MainWork.modules.exam.ResultOfExam;
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
@RequestMapping("/exam")
public class ExamController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/student/{id}")
    public Object getExamsById(@PathVariable(value = "id") int id) throws SQLException {
        Statement stm = dataSource.getConnection().createStatement();
        ResultSet rs = stm.executeQuery("SELECT name,measure,five,two,three,four,data FROM public.standards INNER JOIN " +
                "public.exam ON public.standards.standardid=public.exam.standardid WHERE public.exam.studentid="+id);
        if(!rs.next()){
            rs.close();
            stm.close();
            dataSource.getConnection().close();
            return "error";
        }else{
            LinkedList<ResultOfExam> exam = new LinkedList<>();
            do{
                exam.add(new ResultOfExam(rs.getString(1),rs.getString(2),rs.getDouble(7),rs.getDouble(4),
                        rs.getDouble(5),rs.getDouble(6),rs.getDouble(3)));
            }while (rs.next());
            rs.close();
            stm.close();
            dataSource.getConnection().close();
            if(dataSource.getConnection().isClosed())
            return exam;
            return "error";
        }
    }
}
