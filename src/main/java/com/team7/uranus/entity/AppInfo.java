package com.team7.uranus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("app_info")
public class AppInfo {
    @TableId
    private int appId;
    private String enName;
    private String chName;
    private String department;
    private String contacts;
    private String appDes;
    private String updateTime;
    private int appApplyId;
}
