package com.team7.uranus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team7.uranus.domain.ResponseData;
import com.team7.uranus.entity.UserSample;
import com.team7.uranus.mapper.UserSampleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserSampleMapper userSampleMapper;

    @GetMapping("/api/isAdmin")
    public ResponseData<List<UserSample>> getAdmin(){
        List<UserSample> userSampleList = userSampleMapper.selectList(new QueryWrapper<UserSample>().lambda().eq(UserSample::getIsmanager,1));
        ResponseData<List<UserSample>> orgInfoResponseData = new ResponseData<>();
        orgInfoResponseData.setData(userSampleList);
        return orgInfoResponseData;
    }
}
