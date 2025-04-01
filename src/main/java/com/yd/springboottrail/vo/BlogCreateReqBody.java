package com.yd.springboottrail.vo;

import lombok.Data;

@Data
public class BlogCreateReqBody {
    private String content; // 博客内容
    private int userId;    // 创建博客的用户ID
}
