package com.example.springbootdatamybatis.mapper;


import com.example.springbootdatamybatis.bean.Employee;

public interface EmployeeMapper {

    public Employee getEmpById(Integer id);

    public void insertEmp(Employee employee);
}
