package com.MainWork.controllers;

import com.MainWork.modules.schedule.Schedule;
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
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/{id}")
    public Object getAllScheduleByCoach(@PathVariable(value = "id") int id) throws SQLException {
        if(id<=0) {
            return "error";
        }else {
            Connection con = dataSource.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT scheduleid,schedule.dayid,day.name,schedule.timeid,time.time FROM public.schedule INNER join day ON schedule.dayid = day.dayid INNER JOIN time ON schedule.timeid = time.timeid");
            if (!rs.next()) {
                return "error";
            } else {
                LinkedList<Schedule> schedules = new LinkedList<>();
                do {
                    schedules.add(new Schedule(rs.getInt(1), rs.getInt(2), rs.getString(3), rs.getInt(4),rs.getString(5)));
                } while (rs.next());
                rs.close();
                stm.close();
                con.close();
                return schedules;
            }
        }

    }

    @RequestMapping("/insert/{coachid}/{dayid}/{timeid}")
    public Object insertSchedule(@PathVariable(value = "coachid") int coachid, @PathVariable("dayid") int dayid, @PathVariable("timeid") int timeid) throws SQLException {
        if (coachid <= 0 ||dayid <= 0||timeid<=0) {
            return "error";
        } else {
            Connection con = dataSource.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT public.insert_schedule(" + coachid + ","+dayid+","+timeid+")");
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

    @RequestMapping("/update/{id}/{dayid}/{timeid}")
    public String updateSchedule(@PathVariable(value = "id") int id, @PathVariable("dayid") int dayid, @PathVariable("timeid") int timeid) throws SQLException {
        if (id <= 0 ||dayid <= 0||timeid<=0) {
            return "error";
        } else {
            Connection con = dataSource.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT public.update_schedule(" + id + ","+dayid+","+timeid+")");
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

    @RequestMapping("/delete/{id}")
    public String deleteSchedule(@PathVariable(value = "id") int id) throws SQLException {
        if(id<=0){
            return "error";
        }else{
            Connection con = dataSource.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT public.update_schedule(" + id +")");
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