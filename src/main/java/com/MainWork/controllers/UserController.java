package com.MainWork.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

@RestController
public class UserController {

    @RequestMapping("/authorization")
    public Object Authorizathion (){
        return 25;
    }
}
