package com.yd.springboottrail.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Entity
@Data
@ApiModel(description = "the entity of blog")
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "the id of the blog")
    private Integer id;

    @ApiModelProperty(value = "the content of the blog")
    private String content; // no more than 512 characters

    @ApiModelProperty(value = "the id of the user who created the blog")
    private Integer userId;

    @ApiModelProperty(value = "the number of likes for the blog")
    private Integer likes;

    @ApiModelProperty(value = "the topics associated with the blog. it's a string of topic names separated by commas")
    private String topics; // a string of topic names separated by commas

    public Blog() {
        this.likes = 0;
    }

    public Blog(String content, Integer userId) {
        this.content = content;
        this.userId = userId;
        this.likes = 0;
        this.topics = ""; // default ""
    }

    public Blog(String content, Integer userId, String topics) {
        this.content = content;
        this.userId = userId;
        this.likes = 0;
        this.topics = topics;
    }

    public Blog(Integer id, String content, Integer userId, Integer likes) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.likes = likes;
        this.topics = ""; // default ""
    }

    public Blog(Integer id, String content, Integer userId, String topics) {
        this.id = id;
        this.content = content;
        this.userId = userId;
        this.likes = 0;
        this.topics = topics;
    }
}
