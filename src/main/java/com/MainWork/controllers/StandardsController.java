package com.MainWork.controllers;

import com.MainWork.modules.standard.Standard;
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
@RequestMapping("/standard")
public class StandardsController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/student/{sex}")
    public Object getStundardsBySex(@PathVariable(value = "sex") String sex) throws SQLException {
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM public.standards WHERE sex="+sex);
        if(!rs.next()){
            rs.close();
            stm.close();
            con.close();
            return "error";
        }else{
            LinkedList<Standard> standards = new LinkedList<>();
            do{
               standards.add(new Standard(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4),
                       rs.getDouble(5),rs.getDouble(6),rs.getDouble(7),rs.getString(8)));
            }while (rs.next());
            rs.close();
            stm.close();
            con.close();
            return standards;
        }
    }

}
