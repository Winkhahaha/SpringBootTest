package com.example.springbootweb1.controller;

import com.example.springbootweb1.exception.UserNotExitException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class MyExceptionHandler {

//    @ResponseBody
//    @ExceptionHandler(UserNotExitException.class)
//    public Map<String, Object> handleException(Exception e){
//
//        /*
//        自定义客户端返回的json数据:
//        这种做法使得浏览器和客户端都返回了json数据,没有自适应效果
//         */
//        Map<String,Object> map = new HashMap<>();
//        map.put("code","user.notexit");
//        map.put("message",e.getMessage());
//        return map;
//    }

    @ExceptionHandler(UserNotExitException.class)
    public String handleException(Exception e, HttpServletRequest request) {
        Map<String, Object> map = new HashMap<>();
        //需要传入自己的状态码,不然默认200
        request.setAttribute("javax.servlet.error.status_code",500);
        map.put("code", "user.notexit");
        map.put("message", "用户出错了!");

        request.setAttribute("ext",map);
        //转发到error
        return "forward:/error";
    }
}
