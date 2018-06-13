package com.MainWork.controllers;

import com.MainWork.modules.faculty.Faculty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

@RestController
@RequestMapping("/faculty")
public class FacultyController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/")
    public Object getAllFaculties() throws SQLException{
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM public.\"Faculty\"");
        if(!rs.next()){
            rs.close();
            stm.close();
            con.close();
            return "error";
        }else{
            LinkedList<Faculty> faculties = new LinkedList<>();
            do{
                faculties.add(new Faculty(rs.getInt(1),rs.getString(2)));
            }while(rs.next());
            rs.close();
            stm.close();
            con.close();
            return faculties;
        }
    }

    @RequestMapping("/admin/insert/{name}")
    public String insertFaculty(@PathVariable("name") String name) throws SQLException, UnsupportedEncodingException {
        if(name.isEmpty()||name.equals("")){
            return "error";
        }else {
            Connection con = dataSource.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT insert_faculty('"+ URLDecoder.decode(name,"UTF-8")+"')");
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
    public String updateFaculty(@PathVariable("name") String name,@PathVariable("id") int id) throws SQLException, UnsupportedEncodingException {
        if(id<=0||name.isEmpty()||name.equals("")){
            return "error";
        }else {
            Connection con = dataSource.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT update_faculty("+id+",'"+URLDecoder.decode(name,"UTF-8")+"')");
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
    public String deleteFaculty(@PathVariable("id") int id) throws SQLException {
        if(id<=0){
            return "error";
        }else {
            Connection con = dataSource.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT delete_faculty("+id+")");
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
}
