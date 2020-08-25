package com.example.test.dao;

import com.example.test.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
 public interface StudentMapper {

    @Select("select * from tb_student")
     List<Student> getAllStu();

    @Select("select * from tb_student where id=#{id}")
     Student getStuById(Integer id);

    @Select("select * from tb_student where name like concat('%',#{keyWord},'%');")
     List<Student> getStuList(String keyWord);

    @Delete("delete from tb_student where id =#{id}")
     Integer deleteStuById(Integer id);

    @Options(useGeneratedKeys = true, keyProperty = "id")
    @Insert("insert into tb_student(name,gender,age,birthday,remarks) values(#{name},#{gender},#{age},#{birthday},#{remarks})")
     Integer insertStu(Student student);

    @Update("update tb_student set name=#{name},gender=#{gender},age=#{age},birthday=#{birthday},remarks=#{remarks} where id=#{id}")
     Integer updateStu(Student student);

}
