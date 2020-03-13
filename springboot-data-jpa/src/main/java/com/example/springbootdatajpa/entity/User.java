package com.example.springbootdatajpa.entity;

import lombok.Data;
import javax.persistence.*;

//配置映射关系,使用jpa注解
@Entity //告诉jpa这是一个实体类(和数据表映射的类)
@Table(name = "user")   //指定和哪个数据表对应,如果省略默认表名就是user
@Data
public class User {

    @Id //这是一个主键
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增主键
    private Integer id;

    @Column(name = "lastname") //这是和数据表对应的一个列,省略默认列名就是属性名
    private String lastName;

    @Column
    private String email;
}
