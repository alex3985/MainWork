package com.MainWork.controllers;

import com.MainWork.database.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    DataSource dataSource;
    @RequestMapping("/authorization")
    public Object Authorizathion (){
        return 25;
    }
}
