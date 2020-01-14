package test.cache.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import test.cache.entitys.Employee;
import test.cache.service.EmployeeService;

import javax.annotation.Resource;

@RestController
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @GetMapping("/emp/{id}")
    public Employee getEmp(@PathVariable("id") Integer id){
        return employeeService.getEmp(id);
    }

    @GetMapping("/emp/name/{lastName}")
    public Employee getEmpByName(@PathVariable("lastName") String lastName){
        return employeeService.getEmpByName(lastName);
    }

    @GetMapping("/emp")
    public Employee updateEmp(Employee employee){
        return employeeService.updateEmp(employee);
    }

    @GetMapping("/del/{id}")
    public String deleteEmp(@PathVariable("id") Integer id){
        employeeService.deleteEmp(id);
        return "删除成功!";
    }
}
