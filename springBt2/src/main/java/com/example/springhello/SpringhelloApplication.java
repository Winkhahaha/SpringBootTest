package com.example.springhello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

//@ImportResource(locations = {"classpath:beans.xml"})//导入Spring的配置文件
@SpringBootApplication
public class SpringhelloApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringhelloApplication.class, args);
    }

}
