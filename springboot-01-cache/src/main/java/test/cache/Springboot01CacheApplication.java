package test.cache;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/*
搭建基本环境
@EnableCaching:开启缓存

默认使用的是ConcurrentCacheManager --> ConcurrentMapCache将数据保存在ConcurentMap<Object,Object>
开发中常使用缓存中间件,redis
引入redis之后,使用RedisCacheManager
RedisCacheManager帮我们创建RedisCache来作为缓存组件
 RedisCache通过操作redis来缓存数据,默认保存数据k-v都是Object,利用序列化保存
 如何自动保存json?
 RedisManager操作redis时候使用RedisTemplate<>
 RedisTemplate默认使用jdk序列化机制
 --->自定义CacheManager
 */
@EnableCaching
@MapperScan("test.cache.dao")
@SpringBootApplication
public class Springboot01CacheApplication {

    public static void main(String[] args) {
        SpringApplication.run(Springboot01CacheApplication.class, args);
    }

}
