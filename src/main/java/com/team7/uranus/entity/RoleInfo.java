package com.team7.uranus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("role_info")
public class RoleInfo {
    @TableId
    private int roleId;
    private String roleName;
    private String roleIntro;
}
