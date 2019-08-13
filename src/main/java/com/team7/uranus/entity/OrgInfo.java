package com.team7.uranus.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName
public class OrgInfo {
    private Long org_id;
    private String org_name;
    private String org_description;
    private String org_type;
    private String create_time;
    private String create_person;
    private String state;
    private String apply_description;
    private String auditor;
}
