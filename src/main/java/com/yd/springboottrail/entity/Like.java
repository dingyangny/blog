package com.yd.springboottrail.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id; // 点赞ID
    private Integer targetId; // 目标ID（博客或评论的ID）
    private Integer userId; // 用户ID
    private Integer type; // 类型（1表示博客点赞，2表示评论点赞）

    public Like() {
    }

    public Like(Integer targetId, Integer userId, Integer type) {
        this.targetId = targetId;
        this.userId = userId;
        this.type = type;
    }

    public Like(Integer id, Integer targetId, Integer userId, Integer type) {
        this.id = id;
        this.targetId = targetId;
        this.userId = userId;
        this.type = type;
    }
} 