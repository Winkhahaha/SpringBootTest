package test.cache;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import test.cache.dao.EmployeeMapper;
import test.cache.entitys.Employee;

import javax.annotation.Resource;

@SpringBootTest
class Springboot01CacheApplicationTests {
    @Resource
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;    //操作k-v都是字符串

    @Autowired
    RedisTemplate redisTemplate;        //操作k-v都是对象的

    @Autowired
    RedisTemplate<Object,Employee> empRedisTemplate;

    /**
     * Stirng字符串,list列表,set集合,hash散列,ZSet有序集合
     */
    @Test
    void redisTest01() {
        // 操作字符串
        // 给redis保存数据
        stringRedisTemplate.opsForValue().append("msg","hello");

        //读取数据
        System.out.println(stringRedisTemplate.opsForValue().get("msg"));

        //操作列表
        stringRedisTemplate.opsForList().leftPush("list2","1");
        stringRedisTemplate.opsForList().leftPush("list2","2");
    }

    @Test
    void redisTest02() {
      //保存对象
        Employee emp = employeeMapper.getEmpById(1);
        //默认如果保存对象,使用jdk序列化机制,序列化后的数据保存在redis
        redisTemplate.opsForValue().set("emp01",emp);

        //将数据以json格式保存
        empRedisTemplate.opsForValue().set("emp02",emp);
    }

    @Test
    void contextLoads() {
        Employee employee = employeeMapper.getEmpById(1);
        System.out.println(employee);
    }

}
