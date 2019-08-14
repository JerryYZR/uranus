package com.team7.uranus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("role_user")
public class RoleUser {
    private int id;
    private int roleId;
    private int userId;
}
