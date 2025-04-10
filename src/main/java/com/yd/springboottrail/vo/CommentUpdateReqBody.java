package com.yd.springboottrail.vo;

import lombok.Data;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@Data
@ApiModel(description = "the body of comment update request")
public class CommentUpdateReqBody {
    @ApiModelProperty(value = "the new content of the comment to be updated", required = true)
    private String content; // 评论内容
    @ApiModelProperty(value = "the id of the user who is updaing the comment", required = true)
    private int userId;    // 更新评论的用户ID
}
