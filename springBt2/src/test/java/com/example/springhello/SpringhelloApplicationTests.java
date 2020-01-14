package com.example.springhello;

import com.example.springhello.bean.Person;
import javafx.application.Application;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;
/*
SpringBoot单元测试
可以在测试期间很方便的类似编码一样进行自动注入
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringhelloApplicationTests {

    @Autowired
    Person person;

    @Autowired
    ApplicationContext ioc;

    @Test
    public void testHelloService(){
        System.out.println(ioc.containsBean("helloService"));
    }

    @Test
    public void contextLoads() {
        System.out.println(person);
    }

}
