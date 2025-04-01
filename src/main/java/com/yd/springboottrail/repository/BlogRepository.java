package com.yd.springboottrail.repository;

import com.yd.springboottrail.entity.Blog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog,Integer> {
    List<Blog> findByUserId(Integer userId); // 根据用户 ID 查找博客
}
