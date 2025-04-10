package com.yd.springboottrail.vo;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@ApiModel(description = "the body of comment create request")
public class CommentCreateReqBody {
    @ApiModelProperty(value = "the content of the comment to be created", required = true)
    private String content; // 评论内容
    @ApiModelProperty(value = "the id of the user who is creating a comment", required = true)
    private int userId;    // 创建评论的用户ID
}
