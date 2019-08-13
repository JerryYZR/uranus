package com.team7.uranus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("user_info")
public class User {
    private Long userId;
    private String userName;
    private String password;
    private String nickname;
    private String gender;
    private String email;
    private String addr;
    private String office;
    private String department;
    private String position;
    private Integer ismanager;

}