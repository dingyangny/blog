package com.yd.springboottrail.repository;

import com.yd.springboottrail.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    // 可以在这里添加自定义查询方法
    // 例如：根据用户名查找用户
    Optional<User> findByUsername(String userName);
}