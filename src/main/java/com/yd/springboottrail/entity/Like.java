package com.yd.springboottrail.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "`like`")
@Data
public class Like {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer targetId;
    private Integer userId;
    private Integer type; // blog: 1, comment: 2

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