package com.yd.springboottrail.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "the body of topic create request")
public class TopicCreateReqBody {
    @ApiModelProperty(value = "the name of the topic to be created", required = true)
    private String name; // 话题名称
    @ApiModelProperty(value = "the description of the topic to be created", required = true)
    private String description; // 话题描述
}
