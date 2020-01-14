package xaled;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
将主配置类(@SpringBootApplication标注的类)的所在包及下面所有子包里面的所有组件扫描到Spring容器
 */

@SpringBootApplication//标注一个主程序类,说明这是一个SpringBoot应用(主配置类)
public class SpringBootHello {
    public static void main(String args[]){
        SpringApplication.run(SpringBootHello.class,args);
    }


}
