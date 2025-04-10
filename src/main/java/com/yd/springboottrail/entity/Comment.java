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
@ApiModel(description = "the entity of comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "the id of the comment")
    private Integer id;

    @ApiModelProperty(value = "the id of the blog that the comment belongs to")
    private Integer blogId;

    @ApiModelProperty(value = "the id of the user who made the comment")
    private Integer userId;

    @ApiModelProperty(value = "the content of the comment")
    private String content; // no more than 256 characters

    @ApiModelProperty(value = "the number of likes for the comment")
    private Integer likes;

    public Comment() {
        this.likes = 0;
    }

    public Comment(Integer blogId, Integer userId, String content) {
        this.blogId = blogId;
        this.userId = userId;
        this.content = content;
        this.likes = 0;
    }

    public Comment(Integer id, Integer blogId, Integer userId, String content, Integer likes) {
        this.id = id;
        this.blogId = blogId;
        this.userId = userId;
        this.content = content;
        this.likes = likes;
    }
}
