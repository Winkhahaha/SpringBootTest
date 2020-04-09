package com.example.test.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Student {
    private Integer Id;
    private String name;
    private Byte gender;
    private Integer age;
    //@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    private Date birthday;
    private String remarks;
}
