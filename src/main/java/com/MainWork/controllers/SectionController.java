package com.MainWork.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MainWork.modules.section.Section;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@RestController
@RequestMapping("/section")
public class SectionController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/student/{id}")
    public Object getSectionAndCoachByIdStudent(@PathVariable(value="id") int id) throws SQLException {
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT section.name,coach.name,surname, patronomic FROM public.section INNER JOIN public.coach ON public.coach.coachid=public.section.coachid WHERE sectionid IN" +
                " (SELECT sectionid FROM public.journal WHERE studentid="+id+" )");
        if(rs.next()){
            Section section  = new Section(rs.getString(1),rs.getString(2),rs.getString(3),rs.getString(4));
            rs.close();
            stm.close();
            con.close();
            return section;
        }
        rs.close();
        stm.close();
        con.close();
        return "error";
    }

    @RequestMapping("/coach/{id}")
    public Object getSectionbyCoachid(@PathVariable(value = "id") int id) throws SQLException {
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT sectionid FROM public.section WHERE coachid="+id);
        if(rs.next()){
            int section = rs.getInt(1);
            rs.close();
            stm.close();
            con.close();
            return section;
        }
        rs.close();
        stm.close();
        con.close();
        return "error";

    }


}
