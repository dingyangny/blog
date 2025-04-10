package com.yd.springboottrail.vo;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@ApiModel(description = "the body of blog create request")
public class BlogCreateReqBody {
    @ApiModelProperty(value = "the content of the blog to be created", required = true)
    private String content; // 博客内容

    @ApiModelProperty(value = "the id of the user who is creating a blog", required = true)
    private int userId;    // 创建博客的用户ID

    @ApiModelProperty(value = "the topics related to the blog, it's a string separated by ','", required = false)
    private String topics; // 关联的话题列表，以逗号分隔
}
