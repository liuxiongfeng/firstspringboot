package com.example.firstspringboot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/")
    public String hello(){
        return "this is first springboot project!";
    }

    @RequestMapping("/hello2")
    public String hello2(){
        return "this is second springboot project!";
    }
}
