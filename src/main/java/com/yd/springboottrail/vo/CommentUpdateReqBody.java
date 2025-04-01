package com.yd.springboottrail.vo;

import lombok.Data;

@Data
public class CommentUpdateReqBody {
    private String content; // 评论内容
    private int userId;    // 更新评论的用户ID
}
