package com.yd.springboottrail.vo;

import lombok.Data;

@Data
public class CommentCreateReqBody {
    private String content; // 评论内容
    private int userId;    // 创建评论的用户ID
}
