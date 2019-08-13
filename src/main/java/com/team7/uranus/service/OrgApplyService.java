package com.team7.uranus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.team7.uranus.entity.OrgApplyInfo;

public interface OrgApplyService extends IService<OrgApplyInfo> {
     int confirmApply(OrgApplyInfo orgApplyInfo);
}
