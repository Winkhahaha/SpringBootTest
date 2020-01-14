package com.example.springbootweb1.controller;


import com.example.springbootweb1.exception.UserNotExitException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Arrays;
import java.util.Map;

@Controller
public class HelloController {

    @RequestMapping("/")
    //返回静态资源: template/index
    public String index(){
        return "login";
    }

    @ResponseBody
    @RequestMapping("/hello")
    public String hello(@RequestParam("user") String user){
        if(user.equals("aaa")){
            throw new UserNotExitException();
        }
        return "hello world";
    }

    //查出一些数据在页面展示
    @RequestMapping("/success")
    public String success(Map<String,Object> map){
        map.put("hello","<h1>你好</h1>");
        map.put("users", Arrays.asList("zhangsan","lisi","wangmazi"));
       return "success";
    }
}
