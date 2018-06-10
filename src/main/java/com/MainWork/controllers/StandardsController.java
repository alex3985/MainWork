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

    @RequestMapping("/")
    public Object getStundardsBySex() throws SQLException {
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM public.standards");
        if(!rs.next()){
            rs.close();
            stm.close();
            con.close();
            return "error";
        }else{
            LinkedList<Standard> standards = new LinkedList<>();
            do{
                standards.add(new Standard(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4),
                        rs.getDouble(5),rs.getDouble(6),rs.getDouble(7),rs.getString(8),rs.getString(9)));
            }while (rs.next());
            rs.close();
            stm.close();
            con.close();
            return standards;
        }
    }

    @RequestMapping("/student/{sex}/{id}")
    public Object getStundardsBySex(@PathVariable(value = "sex") String sex,@PathVariable(value = "id") int id) throws SQLException {
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT public.standards.standardid,name,one,two,three,four,five,measure FROM public.standards WHERE sex='"+sex+"' AND NOT standardid IN (SELECT exam.standardid FROM exam WHERE exam.studentid="+id+")");
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

    @RequestMapping("/{id}")
    public Object getStundardById(@PathVariable(value = "id") int id) throws SQLException {
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT public.standards.standardid,name,one,two,three,four,five,measure FROM public.standards WHERE standardid="+id);
        if(!rs.next()){
            rs.close();
            stm.close();
            con.close();
            return "error";
        }else{
            Standard standard = new Standard(rs.getInt(1),rs.getString(2),rs.getDouble(3),rs.getDouble(4),
                        rs.getDouble(5),rs.getDouble(6),rs.getDouble(7),rs.getString(8));
            rs.close();
            stm.close();
            con.close();
            return standard;
        }
    }

    @RequestMapping("/admin/insert/{name}/{one}/{two}/{three}/{four}/{five}/{measure}/{sex}")
    public String insertStandard(@PathVariable("name") String name,@PathVariable("one") double one,
                                 @PathVariable("two") double two,@PathVariable("three") double three,@PathVariable("four") double four,
                                 @PathVariable("five") double five,@PathVariable("measure") String measure,@PathVariable("sex") String sex) throws SQLException {
        if(name.isEmpty()||name.equals("")||one<=0||two<=0||three<=0||four<=0||five<=0||measure.isEmpty()||
                measure.equals("")||sex.isEmpty()||sex.equals("")){
            return "error";
        }else{
            Connection con = dataSource.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT insert_standard('"+name+"',"+one+","+two+","+three+","+four+","+five+
                    ",'"+measure+"','"+sex+"')");
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

    @RequestMapping("/admin/update/{id}/{name}/{one}/{two}/{three}/{four}/{five}/{measure}/{sex}")
    public String updateStandard(@PathVariable("id") int id,@PathVariable("name") String name,@PathVariable("one") double one,
                                 @PathVariable("two") double two,@PathVariable("three") double three,@PathVariable("four") double four,
                                 @PathVariable("five") double five,@PathVariable("measure") String measure,@PathVariable("sex") String sex) throws SQLException {
        if(id<=0||name.isEmpty()||name.equals("")||one<=0||two<=0||three<=0||four<=0||five<=0||measure.isEmpty()||
                measure.equals("")||sex.isEmpty()||sex.equals("")){
            return "error";
        }else{
            Connection con = dataSource.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT update_standard("+id+",'"+name+"',"+one+","+two+","+three+","+four+","+five+
                    ",'"+measure+"','"+sex+"')");
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
    public String deleteStandard(@PathVariable("id") int id) throws SQLException {
        if(id<=0){
            return "error";
        }else {
            Connection con = dataSource.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT delete_standard("+id+")");
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
