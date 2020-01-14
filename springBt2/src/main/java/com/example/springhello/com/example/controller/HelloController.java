package com.example.springhello.com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@ResponseBody//这个类的所有方法返回的数据直接写给浏览器(对象转为json数据)
//@Controller
@RestController
public class HelloController {

    @Value("${person.last-name}")
    private String name;

    @RequestMapping("/hello")
    public String hello(){
        return "hello world,"+name;
    }
}
