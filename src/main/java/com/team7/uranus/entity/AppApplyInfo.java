package com.team7.uranus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("app_apply_info")
public class AppApplyInfo {
    @TableId
    private int appId;
    private String enName;
    private String chName;
    private String department;
    private String contacts;
    private String appDes;
    private String updateTime;
    private int appApplyId;
    private int applyPersonId;
    private String applyTime;
    private String applyType;
    private String applyDesc;
    private Integer state;
    private String auditor;
}
