package com.yd.springboottrail.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 评论ID
    private Integer blogId; // 博客ID
    private Integer userId; // 用户ID
    private String content; // 评论内容
    private Integer likes; // 点赞数（新增字段）

    public Comment() {
        this.likes = 0; // 默认点赞数为0
    }

    public Comment(Integer blogId, Integer userId, String content) {
        this.blogId = blogId;
        this.userId = userId;
        this.content = content;
        this.likes = 0; // 默认点赞数为0
    }

    public Comment(Integer id, Integer blogId, Integer userId, String content, Integer likes) {
        this.id = id;
        this.blogId = blogId;
        this.userId = userId;
        this.content = content;
        this.likes = likes; // 点赞数
    }
}
