package xaled;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Controller
@RequestMapping("/aaa/bbb")
public class MyCon {

    @Resource
    private DataSource dataSource;


    @RequestMapping("hello")
    @ResponseBody       //直接响应到客户端
    public String out(){
        return "hello springboot";
    }
}
