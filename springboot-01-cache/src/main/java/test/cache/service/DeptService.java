package test.cache.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.stereotype.Service;
import test.cache.dao.DepartmentMapper;
import test.cache.entitys.Department;

import javax.annotation.Resource;

@Service
public class DeptService {

    @Resource
    private DepartmentMapper departmentMapper;



    @Cacheable(cacheNames = "dept")
    public Department getDeptById(Integer id){
        System.out.println("查询的部门是:"+id);
        return departmentMapper.getDeptById(id);
    }

    // 编码方式存入缓存:使用缓存管理器api方式操作
    /*
    @Autowired
    private RedisCacheManager redisCacheManager;
    public Department getDeptById(Integer id){
        System.out.println("查询的部门是:"+id);
        Department dept = departmentMapper.getDeptById(id);
        Cache deptCache = redisCacheManager.getCache("dept");
        deptCache.put("dept1",dept);
        return dept ;
    }
    */

}
