package com.MainWork.controllers;

import com.MainWork.modules.users.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
@RequestMapping("/section")
public class SectionController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/student/{id}")
    public String getSectionByIdStudent(@PathVariable(value="id") int id) throws SQLException {
        Statement stm = dataSource.getConnection().createStatement();
        ResultSet rs = stm.executeQuery("SELECT name FROM public.section WHERE sectionid IN" +
                " (SELECT sectionid FROM public.journal WHERE studentid="+id+" )");
        if(rs.next()){
            String name = new String(rs.getString(1));
            rs.close();
            stm.close();
            dataSource.getConnection().close();
            return name;
        }
        rs.close();
        stm.close();
        dataSource.getConnection().close();
        return "error";
    }
}
