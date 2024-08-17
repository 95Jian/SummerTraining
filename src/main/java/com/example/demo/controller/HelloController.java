package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello world";
    }

    @RequestMapping(value="/getTest",method = RequestMethod.GET)
    public String getTest(String name,String password){
        return "GET请求";
    }


}
