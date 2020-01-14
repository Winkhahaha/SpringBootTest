package com.example.springbootweb1.component;


import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.WebRequest;

import java.util.Map;

@Component//给容器中加入我们自己定义的
public class MyErrorAttributes extends DefaultErrorAttributes {

    @Override
    public Map<String, Object> getErrorAttributes(WebRequest webRequest, boolean includeStackTrace) {
        Map<String,Object> map=super.getErrorAttributes(webRequest,includeStackTrace);

        //我们的异常处理器携带的数据,将显示在客户端返回的json数据中
        map.put("school","xaled");
        Map<String,Object> ext = (Map<String, Object>) webRequest.getAttribute("ext", 0);
        map.put("ext",ext);
        return map;
    }
}
