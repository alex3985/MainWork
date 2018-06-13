package com.MainWork.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MainWork.modules.section.Section;
import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

@RestController
@RequestMapping("/section")
public class SectionController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/")
    public Object getAllSections() throws SQLException{
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM public.section");
        if(!rs.next()){
            rs.close();
            stm.close();
            con.close();
            return "error";
        }else{
            LinkedList<Section> sections = new LinkedList<>();
            do{
                sections.add(new Section(rs.getInt(1),rs.getString(2)));
            }while(rs.next());
            rs.close();
            stm.close();
            con.close();
            return sections;
        }
    }

    @RequestMapping("/admin/insert/{name}")
    public String insertSection(@PathVariable("name") String name) throws SQLException, UnsupportedEncodingException {
        if(name.isEmpty()||name.equals("")){
            return "error";
        }else {
            Connection con = dataSource.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT insert_section('"+ URLDecoder.decode(name,"UTF-8")+"')");
            if (rs.next()) {
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

    @RequestMapping("/admin/update/{name}/{id}")
    public String updateSection(@PathVariable("name") String name,@PathVariable("id") int id) throws SQLException {
        if(id<=0||name.isEmpty()||name.equals("")){
            return "error";
        }else {
            Connection con = dataSource.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT update_section("+id+",'"+name+"')");
            if (rs.next()) {
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

    @RequestMapping("/admin/delete/{id}")
    public String deleteSection(@PathVariable("id") int id) throws SQLException {
        if(id<=0){
            return "error";
        }else {
            Connection con = dataSource.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT delete_section("+id+")");
            if (rs.next()) {
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

    @RequestMapping("/student/{id}")
    public Object getSectionAndCoachByIdStudent(@PathVariable(value="id") int id) throws SQLException {
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT section.name,coach.name,surname, patronomic FROM public.section INNER JOIN public.coach ON public.coach.sectionid=public.section.sectionid WHERE coach.coachid IN" +
                " (SELECT journal.coachid FROM public.journal WHERE studentid="+id+" )");
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
        ResultSet rs = stm.executeQuery("SELECT sectionid FROM public.coach WHERE coachid="+id);
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
