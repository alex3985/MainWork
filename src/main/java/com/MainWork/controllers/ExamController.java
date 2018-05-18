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
        ResultSet rs = stm.executeQuery("SELECT name,measure,\"1\",\"2\",\"3\",\"4\",\"5\",data FROM public.\"Standards\" INNER JOIN " +
                "public.\"Exam\" ON public.\"Standards\".standardid=public.\"Exam\".standardid WHERE public.\"Standards\".standardid="+1);
        if(!rs.next()){
            rs.close();
            stm.close();
            dataSource.getConnection().close();
            return "error";
        }else{
            LinkedList<ResultOfExam> exam = new LinkedList<>();
            do{
                exam.add(new ResultOfExam(rs.getString(1),rs.getString(2),rs.getDouble(3)));
            }while (rs.next());
            rs.close();
            stm.close();
            dataSource.getConnection().close();
            return exam;
        }
    }
}
