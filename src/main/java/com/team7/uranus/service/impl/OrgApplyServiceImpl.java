package com.team7.uranus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team7.uranus.Exception.MyException;
import com.team7.uranus.entity.OrgApplyInfo;
import com.team7.uranus.entity.OrgInfo;
import com.team7.uranus.mapper.OrgApplyInfoMapper;
import com.team7.uranus.service.OrgApplyService;
import com.team7.uranus.service.OrgInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;

@Transactional
@Service
public class OrgApplyServiceImpl extends ServiceImpl<OrgApplyInfoMapper, OrgApplyInfo> implements OrgApplyService {

    @Autowired
    OrgInfoService orgInfoService;

    @Autowired
    OrgApplyService orgApplyService;

    @Override
    public int confirmApply(OrgApplyInfo orgApplyInfo) {
        String applyType = orgApplyInfo.getApplyType();
        if("新建".equals(applyType)){
            OrgInfo orgInfo = new OrgInfo(orgApplyInfo.getOrgName(),
                    orgApplyInfo.getOrgDesc(),orgApplyInfo.getType(), LocalDateTime.now().toString());
            orgInfoService.save(orgInfo);
        }else if("修改".equals(applyType)) {
            //get information of organization and update
            OrgInfo orgInfo = orgInfoService.lambdaQuery().getBaseMapper().selectById(orgApplyInfo.getOrgId());
            orgInfo.setOrgDesc(orgApplyInfo.getOrgDesc());
            orgInfo.setOrgName(orgApplyInfo.getOrgName());
            orgInfo.setType(orgApplyInfo.getType());
            orgInfoService.saveOrUpdate(orgInfo);
        }else if ("删除".equals(applyType)){
            orgInfoService.removeById(orgApplyInfo.getOrgId());
        }else {
            throw new MyException(667,"该修改方式非法");
        }
        //change orgApply state and update
        orgApplyInfo.setState(1);
        orgApplyService.saveOrUpdate(orgApplyInfo);
        return 0;
    }
}
