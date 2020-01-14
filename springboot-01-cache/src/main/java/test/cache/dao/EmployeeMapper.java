package test.cache.dao;

import org.apache.ibatis.annotations.*;
import test.cache.entitys.Employee;

@Mapper
public interface EmployeeMapper {

    @Select("select * from employee where id = #{id}")
    public Employee getEmpById(Integer id);

    @Update("update employee set lastName=#{lastName},email=#{email},gender=#{gender},d_id=#{dId} where id = #{id}")
    public Integer updateEmp(Employee employee);

    @Delete("delete from employee where id = #{id}")
    public void deleteEmp(Integer id);

    @Insert("insert into employee (lastName,email,gender,d_id) values(#{lastName},#{email},#{gender})")
    public void insertEmp(Employee employee);

    @Select("select * from employee where lastName = #{lastName}")
    Employee getEmpByName(String lastName);
}
