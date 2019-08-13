package com.team7.uranus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team7.uranus.Exception.MyException;
import com.team7.uranus.domain.ResponseData;
import com.team7.uranus.entity.OrgApplyInfo;
import com.team7.uranus.mapper.OrgApplyInfoMapper;
import com.team7.uranus.service.OrgApplyService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.NumberUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@Slf4j
@RestController
public class OrgApplyController {

    @Autowired
    private OrgApplyInfoMapper orgApplyInfoMapper;

    @Autowired
    private OrgApplyService orgApplyService;

    @PostMapping("/api/orgApply")
    public ResponseData postOrgApply(@RequestBody OrgApplyInfo orgApplyInfo) {
        orgApplyInfo.setApplyTime(LocalDateTime.now().toString());
        orgApplyInfo.setUpdateTime(LocalDateTime.now().toString());
        orgApplyInfo.setState(0);
        orgApplyInfoMapper.insert(orgApplyInfo);
        return new ResponseData<>(200, "success", "success");
    }

    @GetMapping("/api/orgApply")
    public ResponseData getApply(@RequestParam int pageNum,@RequestParam String state,@RequestParam String orgName) {
        Page<OrgApplyInfo> orgInfoPage = new Page<>();
        orgInfoPage.setPages(pageNum);
        orgInfoPage.setSize(10);
        IPage<OrgApplyInfo> page = orgApplyInfoMapper.selectPage(orgInfoPage, Wrappers.<OrgApplyInfo>query().lambda().
                like(OrgApplyInfo::getOrgName,orgName).like(OrgApplyInfo::getState,state).orderBy(true, true, OrgApplyInfo::getState));
        ResponseData orgInfoResponseData = new ResponseData<>();
        orgInfoResponseData.setData(page);
        return orgInfoResponseData;
    }

    @GetMapping("/api/orgApply/{id}")
    public ResponseData getChangeApply(@PathVariable int orgApplyId) {
        OrgApplyInfo orgApplyInfo = orgApplyInfoMapper.selectById(orgApplyId);
        ResponseData<OrgApplyInfo> r = new ResponseData<>();
        r.setData(orgApplyInfo);
        return r;
    }

    @PutMapping("/api/confirm/{orgApplyId}")
    public ResponseData confirm(@PathVariable int orgApplyId,@RequestBody Map isAgreeBody){
        String isAgree = (String) isAgreeBody.get("isAgree");
        if(isAgree==null){
            throw new MyException(669,"确认你的要求");
        }
        OrgApplyInfo orgApplyInfo = orgApplyInfoMapper.selectById(orgApplyId);
        if(orgApplyInfo.getState()>=1){
            throw new MyException(668,"该工单已经被审核了");
        }
        if(isAgree.equals("false")){
            orgApplyInfo.setState(2);
            orgApplyInfoMapper.updateById(orgApplyInfo);
        }else {
            orgApplyService.confirmApply(orgApplyInfo);
        }
        return new ResponseData(200,"success","success");
    }
}
