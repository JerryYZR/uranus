package com.team7.uranus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.annotation.Generated;

@Data
@AllArgsConstructor
@TableName("org_info")
public class OrgInfo {
    @TableId
    private int orgId;
    private String orgName;
    private String orgDesc;
    private String type;
    private String createTime;

    public OrgInfo(String orgName,String orgDesc,String type,String createTime){
        this.orgName = orgName;
        this.orgDesc = orgDesc;
        this.type = type;
        this.createTime = createTime;
    }
}
