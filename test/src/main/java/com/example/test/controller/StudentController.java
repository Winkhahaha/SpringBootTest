package com.example.test.controller;

import com.example.test.entity.Student;
import com.example.test.exception.NotFoundException;
import com.example.test.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/stu")
public class StudentController {
    @Resource
    private StudentService studentService;

    @GetMapping
    public String getAllStu(Model model) {
        List<Student> students = studentService.getAllStu();
        model.addAttribute("students", students);
        return "stu/list";
    }

    @GetMapping("{id}")
    public String getStuById(@PathVariable("id") Integer id, Model model) {
        Student stu = studentService.getStuById(id);
        model.addAttribute("student", stu);
        return "stu/student";
    }

    /**
     * 姓名模糊查询
     *
     * @param keyWord 查询关键字
     * @return
     */
    @PostMapping("/name")
    public String getStuList(@RequestParam("keyWord") String keyWord, Model model) {
        List<Student> stuList = studentService.getStuList(keyWord);
        model.addAttribute("stuList", stuList);
        return "stu/otherList";

    }

    @GetMapping("/delete/{id}")
    public String deleteStuById(@PathVariable("id") Integer id) {
        Integer i = studentService.deleteStuById(id);
        if (i != 1) {
            throw new NotFoundException("没有该学生");
        }
        return "redirect:/stu";
    }

    /**
     * 创建学生对象的表单
     *
     * @param model
     * @return
     */
    @GetMapping("/form")
    public String createForm(Model model) {
        return "stu/form";
    }

    @PostMapping("/add")
    public String insertStu(Student student, HttpServletRequest req) {
        if (student != null) {
            if (student.getName() == null || student.getName().equals("")) {
                req.getSession().setAttribute("errors", "姓名不能为空！");
                return "redirect:/stu/form";
            } else if (student.getAge() > 120 || student.getAge() < 0) {
                req.getSession().setAttribute("errors", "年龄输入错误！");
                return "redirect:/stu/form";
            } else if (student.getGender() > 1 || student.getGender() < 0) {
                req.getSession().setAttribute("errors", "性别输入错误！");
                return "redirect:/stu/form";
            }
        }
        Integer i = studentService.insertStu(student);
        if (i != 1) {
            throw new NotFoundException("添加失败");
        }
        return "redirect:/stu";
    }

    /**
     * 修改时,先进入预编辑界面
     *
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/edit/{id}")
    public String editStu(@PathVariable("id") Integer id, Model model) {
        Student student = studentService.getStuById(id);
        if (student != null) {
            model.addAttribute("student", student);
        } else {
            throw new NotFoundException("该学生不存在");
        }
        return "stu/update";
    }

    /**
     * 预编辑界面提交后,执行更新操作
     *
     * @param student
     * @return
     */
    @PostMapping("/update")
    public String updateStu(Student student) {
        if (student != null) {
            Integer i = studentService.updateStu(student);
            if (i != 1) {
                throw new NotFoundException("该学生不存在");
            }
        }
        return "redirect:/stu";
    }
}
