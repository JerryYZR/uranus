package com.team7.uranus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team7.uranus.domain.ResponseData;
import com.team7.uranus.entity.OrgApplyInfo;
import com.team7.uranus.entity.OrgInfo;
import com.team7.uranus.mapper.OrgApplyInfoMapper;
import com.team7.uranus.mapper.OrgInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class OrgApplyController {

    @Autowired
    private OrgApplyInfoMapper orgApplyInfoMapper;


    @PostMapping("/api/orgApply")
    public ResponseData postOrgApply(@RequestBody OrgApplyInfo orgApplyInfo) {
        orgApplyInfo.setApplyTime(LocalDateTime.now().toString());
        orgApplyInfo.setUpdateTime(LocalDateTime.now().toString());
        orgApplyInfo.setState(0);
        orgApplyInfoMapper.insert(orgApplyInfo);
        return new ResponseData<>(200, "success", "success");
    }

    @GetMapping("/api/orgApply")
    public ResponseData getApply(@RequestParam int pageNum) {
        Page<OrgApplyInfo> orgInfoPage = new Page<>();
        orgInfoPage.setPages(pageNum);
        orgInfoPage.setSize(10);
        IPage<OrgApplyInfo> page = orgApplyInfoMapper.selectPage(orgInfoPage, Wrappers.<OrgApplyInfo>query().orderBy(true, false, "state"));
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

}
