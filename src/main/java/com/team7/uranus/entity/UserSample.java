package com.team7.uranus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_info")
public class UserSample {
    @TableId
    private int userId;
    private String userName;
    private String nickname;
    private Integer ismanager;
}
