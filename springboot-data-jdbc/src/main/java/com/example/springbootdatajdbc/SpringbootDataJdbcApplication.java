package com.example.springbootdatajdbc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
public class SpringbootDataJdbcApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootDataJdbcApplication.class, args);
    }

}
