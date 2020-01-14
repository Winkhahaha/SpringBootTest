package com.example.springhello.bean;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

/*
将配置文件中的每一个属性的值映射到这个组件
只有这个组件是容器中的组件才能使用容器提供的@ConfigurationProperties功能
 */
@Component
@ConfigurationProperties(prefix = "person")//将本类中的所有属性和配置(prefix = "person")文件中相关的配置进行绑定
@Data
//@PropertySource(value = {"classpath:person.properties"})
//@Validated//校验
public class Person {

    //@Value("${person.last-name}")//越过校验
    //@Email//lastName必须是邮箱格式:@con强制校验
    private String lastName;
    //@Value("#{11*2}")
    private Integer age;
    private Boolean boss;
    private Date birth;

    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;

    @Override
    public String toString() {
        return "Person{" +
                "lastName='" + lastName + '\'' +
                ", age=" + age +
                ", boss=" + boss +
                ", birth=" + birth +
                ", maps=" + maps +
                ", lists=" + lists +
                ", dog=" + dog +
                '}';
    }
}
