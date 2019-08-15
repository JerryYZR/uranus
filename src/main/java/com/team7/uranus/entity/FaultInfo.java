package com.team7.uranus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("fault_info")
public class FaultInfo {
    @TableId
    private int faultId;
    private String faultTitle;
    private int status;//null
    private String faultApplication;
    private int linkUserId;
    private String linkMan;
    private String linkPhone;
    private String createdTime;
    private String submitedTime;
    private String handledTime;
    private String content;
    private String result;//null
}
