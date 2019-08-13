package com.team7.uranus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("org_info")
public class OrgInfo {
    @TableId
    private Long orgId;
    private String orgName;
    private String orgDesc;
    private String type;
    private String createTime;
}
