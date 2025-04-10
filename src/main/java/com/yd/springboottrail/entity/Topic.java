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
@ApiModel(description = "the entity of topic")
public class Topic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "the id of the topic")
    private int id;

    @ApiModelProperty(value = "the name of the topic, unique and no more than 10 characters")
    private String name; // unique and no more than 10 characters

    @ApiModelProperty(value = "the description of the topic")
    private String description; // no more than 256 characters

    // constructors
    public Topic() {
    }

    public Topic(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public Topic(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }
}
