package com.MainWork.controllers;

import com.MainWork.modules.admin.StudentForAdmin;
import com.MainWork.modules.authorization.User;
import com.MainWork.modules.users.Coach;
import com.MainWork.modules.users.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.sql.DataSource;
import java.sql.*;
import java.util.LinkedList;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private DataSource dataSource;

    @RequestMapping("/authorization/{login}/{password}")
    public Object Authorizathion (@PathVariable(value = "login") String login,@PathVariable(value = "password") String password) throws SQLException, ClassNotFoundException {
        Connection con =dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM public.users WHERE password="+"'"+password+"' AND login="+"'"+login+"'");
        ResultSet rs = stmt.executeQuery();
        if(rs.next()) {
            User user =new User(rs.getInt(1),null,null,rs.getInt(4),rs.getInt(5),rs.getInt(6));
            rs.close();
            stmt.close();
            con.close();
            return user;
        }
        rs.close();
        stmt.close();
        con.close();
        return "error";
    }


    @RequestMapping("/admin/user/insert/student/{studentid}/{login}/{password}")
    public Object insertUserStudent(@PathVariable(value = "studentid") int studentid,@PathVariable("login") String login,@PathVariable("password") String password) throws SQLException {
        if(studentid<=0||login.isEmpty()||login.equals("")||password.equals("")||password.isEmpty())
        {
            return "error";
        }else{
            Connection con = dataSource.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT public.insert_user_student("+studentid+",'"+login+"','"+password+"')");
            rs.next();
            String massage=rs.getString(1);
            rs.close();
            stm.close();
            con.close();
            return massage ;
        }
    }

    @RequestMapping("/admin/user/update/student/{studentid}/{login}/{password}")
    public Object updateUserStudent(@PathVariable(value = "studentid") int studentid,@PathVariable("login") String login,@PathVariable("password") String password) throws SQLException {
        if(studentid<=0||login.isEmpty()||login.equals("")||password.equals("")||password.isEmpty())
        {
            return "error";
        }else{
            Connection con = dataSource.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT public.update_user_student("+studentid+",'"+login+"','"+password+"')");
            rs.next();
            String massage=rs.getString(1);
            rs.close();
            stm.close();
            con.close();
            return massage ;
        }
    }

    @RequestMapping("/admin/user/delete/student/{studnetid}")
    public String deleteUserStudent(@PathVariable(value = "studentid") int studentid) throws SQLException {
        if(studentid<=0)
        {
            return "error";
        }else{
            Connection con = dataSource.getConnection();
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery("SELECT public.delete_user_student("+studentid+")");
            rs.next();
            String massage=rs.getString(1);
            rs.close();
            stm.close();
            con.close();
            return massage ;
        }
    }

    @RequestMapping("/admin/user/student/")
    public Object deleteUserStudent() throws SQLException {
        Connection con = dataSource.getConnection();
        Statement stm = con.createStatement();
        ResultSet rs = stm.executeQuery("SELECT student.studentid,student.surname,student.name,student.patronymic,public.\"Faculty\".name, student.grou, section.name, foo.surname,foo.name,foo.patronomic,users.login,users.password FROM student\n" +
                "  INNER JOIN (Select journal.studentid,journal.sectionid,coach.name,coach.patronomic,coach.surname FROM coach INNER JOIN journal ON coach.coachid = journal.coachid) AS foo ON student.studentid=foo.studentid INNER JOIN public.\"Faculty\"\n" +
                "    ON student.facultyid=public.\"Faculty\".facultyid INNER JOIN section ON foo.sectionid=section.sectionid LEFT OUTER JOIN users ON student.studentid = users.studentid;");
        rs.next();
        LinkedList<StudentForAdmin> students = new LinkedList<>();
        do{
            students.add(new StudentForAdmin(rs.getInt(1),rs.getString(2)+" "+rs.getString(3)+" "+rs.getString(4),rs.getString(5),rs.getString(6),
                    rs.getString(7),rs.getString(8)+" "+rs.getString(9)+" "+rs.getString(10),rs.getString(11),rs.getString(12)));
        }while (rs.next());
        rs.close();
        stm.close();
        con.close();
        return students ;
    }

}
