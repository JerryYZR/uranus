package com.team7.uranus.controller;

import com.team7.uranus.domain.ResponseData;
import com.team7.uranus.entity.OrgInfo;
import com.team7.uranus.mapper.OrgInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class OrgInfoController {
    @Autowired
    private OrgInfoMapper orgInfoMapper;

    @GetMapping("/api/orgInfo")
    public ResponseData getOrgInfo(){
        List<OrgInfo> orgInfoList = orgInfoMapper.selectList(null);
        ResponseData<List<OrgInfo>> orgInfoResponseData = new ResponseData<>();
        orgInfoResponseData.setData(orgInfoList);
        return orgInfoResponseData;
    }

//    @PostMapping("/api/orgInfo")
//    public ResponseData addOrgInfo( ){
//
//    }
}
