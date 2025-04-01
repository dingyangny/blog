package com.yd.springboottrail.repository;

import com.yd.springboottrail.entity.Comment;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    // find by blogId
    List<Comment> findByBlogId(Integer blogId);
    // find by userId
    List<Comment> findByUserId(Integer userId);
    // delete by blogId
    void deleteByBlogId(Integer blogId);
} 