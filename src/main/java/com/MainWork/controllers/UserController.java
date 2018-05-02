package com.MainWork.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @RequestMapping("/authorization")
    public Object Authorizathion (){
        return 25;
    }
}
