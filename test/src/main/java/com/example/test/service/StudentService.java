package com.example.test.service;

import com.example.test.dao.StudentMapper;
import com.example.test.entity.Student;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class StudentService {

    @Resource
    private StudentMapper studentMapper;

    public List<Student> getAllStu() {
        return studentMapper.getAllStu();
    }

    public Student getStuById(Integer id) {
        return studentMapper.getStuById(id);
    }

    public List<Student> getStuList(String keyWord) {
        return studentMapper.getStuList(keyWord);
    }

    @Transactional
    public Integer deleteStuById(Integer id) {
        return studentMapper.deleteStuById(id);
    }

    @Transactional
    public Integer insertStu(Student student) {
        return studentMapper.insertStu(student);
    }

    @Transactional
    public Integer updateStu(Student student) {
        return studentMapper.updateStu(student);
    }
}
