package com.yd.springboottrail.vo;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@ApiModel(description = "the body of blog update request")
public class BlogUpdateReqBody {
    @ApiModelProperty(value = "the content of the blog to be updated", required = true)
    private String content; // 博客内容

    @ApiModelProperty(value = "the id of the user who is updating the blod", required = true)
    private int userId; // 更新博客的用户ID

    @ApiModelProperty(value = "the topics related to the blog, it's a string separated by ','", required = false)
    private String topics; // 关联的话题列表，以逗号分隔
}
