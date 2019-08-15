package com.team7.uranus.controller;

//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team7.uranus.domain.ResponseData;
import com.team7.uranus.entity.OrgInfo;
import com.team7.uranus.mapper.OrgInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class OrgInfoController {
    @Autowired
    private OrgInfoMapper orgInfoMapper;

    @GetMapping("/api/orgInfo")
    public ResponseData getOrgInfo(@RequestParam int pageNum,@RequestParam String orgName) {
        Page<OrgInfo> orgInfoPage = new Page<>();
        orgInfoPage.setPages(pageNum);
        orgInfoPage.setSize(10);
        IPage<OrgInfo> page = orgInfoMapper.selectPage(orgInfoPage, new QueryWrapper<OrgInfo>().lambda().like(OrgInfo::getOrgName,orgName));
        ResponseData orgInfoResponseData = new ResponseData<>();
        orgInfoResponseData.setData(page);
        return orgInfoResponseData;
    }

    @GetMapping("/api/orgInfo/{orgId}")
    public ResponseData<OrgInfo> getChangeApply(@PathVariable int orgId) {
        OrgInfo orgInfo = orgInfoMapper.selectById(orgId);
        ResponseData<OrgInfo> r = new ResponseData<>();
        r.setData(orgInfo);
        return r;
    }

    @PostMapping("/api/admin/orgInfo")
    public ResponseData addOrgInfo(@RequestBody OrgInfo orgInfo) {
        orgInfoMapper.insert(orgInfo);
        return new ResponseData<>(200, "success", "success");
    }


}
