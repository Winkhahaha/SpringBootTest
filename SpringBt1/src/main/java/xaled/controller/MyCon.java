package xaled.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MyCon {

    @RequestMapping("hello")
    @ResponseBody       //直接响应到客户端
    public String out(){
        return "hello springboot";
    }
}
