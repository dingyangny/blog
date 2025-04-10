package com.yd.springboottrail.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "the body of user update request")
public class UserUpdateReqBody {
    @ApiModelProperty(value = "the new name of the user to be updated", required = false)
    private String username; // 用户名
    @ApiModelProperty(value = "the new password of the user to be updated", required = false)
    private String password; // 密码
}
