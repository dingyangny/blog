package com.yd.springboottrail.vo;

import lombok.Data;

@Data
public class BlogUpdateReqBody {
    private String content; // 博客内容
    private int userId; // 更新博客的用户ID
}
