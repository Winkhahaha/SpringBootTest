package com.example.springbootweb1.controller;


import com.example.springbootweb1.dao.DepartmentDao;
import com.example.springbootweb1.dao.EmployeeDao;
import com.example.springbootweb1.entities.Department;
import com.example.springbootweb1.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Controller
public class EmployeeController {

    @Autowired
    EmployeeDao employeeDao;

    @Autowired
    DepartmentDao departmentDao;

    //查询所有员工返回列表页面
    @GetMapping("/emps")
    public String list(Model model) {
        Collection<Employee> employees = employeeDao.getAll();

        //放在请求域
        model.addAttribute("emps", employees);

        //thymeleaf默认就会拼串
        // classpath:/templates/xxxx.html
        return "emp/list";
    }

    //来到员工添加页面
    @GetMapping("/emp")
    public String toAddPage(Model model) {
        //来到添加页面,查出所有的部门,在页面显示
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        return "emp/add";
    }

    //员工添加
    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        //保存员工
        employeeDao.save(employee);
        //来到员工列表页面
        return "redirect:/emps";

    }

    //来到修改页面,查出当前员工,在页面回显
    @GetMapping("/emp/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        Employee employee = employeeDao.get(id);
        model.addAttribute("emp", employee);

        //页面显示部门列表
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("depts", departments);
        //回到修改页面
        //add是修改添加二合一
        return "emp/add";

    }

    //员工修改,需要员工id
    @PutMapping("/emp")
    public String updateEmployee(Employee employee){
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    //员工删除
    @DeleteMapping("/emp/{id}")
    public String deleteEmployee(@PathVariable("id") Integer id){
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
