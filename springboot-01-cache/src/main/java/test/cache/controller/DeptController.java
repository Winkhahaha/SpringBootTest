package test.cache.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import test.cache.entitys.Department;
import test.cache.service.DeptService;

import javax.annotation.Resource;

@RestController
public class DeptController {

    @Resource
    private DeptService deptService;

    @GetMapping("/dept/{id}")
    public Department getDept(@PathVariable("id") Integer id) {
        return deptService.getDeptById(id);
    }
}
