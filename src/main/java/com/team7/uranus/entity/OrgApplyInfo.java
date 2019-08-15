package com.team7.uranus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("org_apply_info")
public class OrgApplyInfo {
    private int orgId;
    private String orgName;
    private String orgDesc;
    private String type;
    private String updateTime;
    @TableId
    private int orgApplyId;
    private String applyPerson;
    private String applyTime;
    private String applyType;
    private String applyDesc;
    private Integer state;
    private String auditor;
}
