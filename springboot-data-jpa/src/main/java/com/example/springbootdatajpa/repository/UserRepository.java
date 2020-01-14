package com.example.springbootdatajpa.repository;

import com.example.springbootdatajpa.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

//继承Repository来完成对数据库操作
public interface UserRepository extends JpaRepository<User,Integer> {
}
