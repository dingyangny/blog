package com.yd.springboottrail.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String content;
    private Integer userId;
    private Integer likes;

    public Blog() {
        this.likes = 0;
    }

    public Blog(String content, Integer userId) {
        this.content = content;
        this.userId = userId;
        this.likes = 0;
    }

    public Blog(Integer id, String content, Integer userId, Integer likes) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.likes = likes;
    }
}
