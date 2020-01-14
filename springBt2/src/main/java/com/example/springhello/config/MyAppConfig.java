package com.example.springhello.config;


import com.example.springhello.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//指明当前类是一个配置类,来替代Spring配置文件
public class MyAppConfig {

    @Bean//将方法的返回值添加到容器中,容器中这个组件默认的id就是方法名
    public HelloService helloService(){
        System.out.println("配置类bean给容器中添加组件");
        return new HelloService();
    }
}
