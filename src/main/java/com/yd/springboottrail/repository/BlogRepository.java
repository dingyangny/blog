package com.yd.springboottrail.repository;

import com.yd.springboottrail.entity.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Blog,Integer> {
}
