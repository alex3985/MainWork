package com.MainWork.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;

@RestController
public class HomeController {
    @RequestMapping("/hello")
    public LinkedList<String> home(){
        LinkedList<String> a = new LinkedList<>();
        a.add("Влада");
        a.add("Ракло");
        return a;
    }
}
