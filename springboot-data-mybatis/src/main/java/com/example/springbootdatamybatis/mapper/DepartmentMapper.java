package com.example.springbootdatamybatis.mapper;

import com.example.springbootdatamybatis.bean.Department;
import org.apache.ibatis.annotations.*;

//@Mapper//指定这是一个操作数据库的Mapper
public interface DepartmentMapper {

    @Select("select * from department where id=#{id}")
    public Department getDeptById(Integer id);

    @Delete("delete from department where id =#{id}")
    public Integer deleteDeptById(Integer id);

    @Options(useGeneratedKeys = true,keyProperty = "id")
    @Insert("insert into department(department_name) values(#{departmentName})")
    public Integer insertDept(Department department);

    @Update("update department set department_name=#{departmentName} where id=#{id}")
    public Integer updateDept(Department department);

}
