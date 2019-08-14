package com.team7.uranus.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.team7.uranus.domain.ResponseData;
import com.team7.uranus.entity.FaultInfo;

import com.team7.uranus.entity.User;
import com.team7.uranus.mapper.FaultInfoMapper;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.time.LocalDateTime;
import java.util.List;


@RestController
public class FaultInfoController {
    @Autowired
    private FaultInfoMapper faultInfoMapper;



    //根据条件查询,(status,faultApplication,faultTitle)为查询的3个条件，其中前两个是精确查询，最后一个是模糊查询。
    @GetMapping("/api/faultInfo")
    public ResponseData getFaultInfo(@RequestParam Integer pageNum,@RequestParam Integer status,@RequestParam String faultApplication,@RequestParam String faultTitle){ //状态，应用，故障标题
        Page<FaultInfo> faultInfoPage = new Page<>();
        faultInfoPage.setPages(pageNum);
        faultInfoPage.setSize(10);
        //if(status==null)
        IPage<FaultInfo> page = faultInfoMapper.selectPage(faultInfoPage, Wrappers.<FaultInfo>query().lambda()
                .eq(status!=null,FaultInfo::getStatus,status).eq(!faultApplication.isEmpty(),FaultInfo::getFaultApplication,faultApplication)
                .like(faultTitle!=null,FaultInfo::getFaultTitle,faultTitle));
        ResponseData faultInfoResponseData = new ResponseData<>();
        faultInfoResponseData.setData(page);
        return faultInfoResponseData;
    }

    //详细页，点击对应数据进入详细信息界面，根据传回的faultId得到对应故障单。
    @GetMapping("/api/faultInfo/{faultId}")
    public ResponseData<FaultInfo> getFaultAndOperateInfo(@PathVariable int faultId) {
        FaultInfo faultInfo = faultInfoMapper.selectById(faultId);
        /*
        List<FaultOperateInfo>  faultOperateInfo = faultOperateInfoMapper.selectList(new QueryWrapper<FaultOperateInfo>().lambda().eq(FaultOperateInfo::getFaultId, faultId));

        FaultAndOperateInfo faultAndOperateInfo = new FaultAndOperateInfo(faultInfo,faultOperateInfo);
        */
        ResponseData<FaultInfo> faultInfoResponse = new ResponseData<>();

        faultInfoResponse.setData(faultInfo);

        return faultInfoResponse;
    }





    //提交故障单
    @PostMapping("/api/faultInfoSub")
    public ResponseData addfaultInfo(@RequestBody FaultInfo faultInfo) {
        //自己添加
        //添加到故障信息表中
        //faultInfo.setCreatedTime(LocalDateTime.now().toString());
        faultInfo.setStatus(0);
        faultInfo.setSubmitedTime(LocalDateTime.now().toString());
        faultInfoMapper.insert(faultInfo);


        /*
        FaultOperateInfo faultOperateInfo = new FaultOperateInfo();
        faultOperateInfo.setFaultId(faultInfo.getFaultId());
        faultOperateInfo.setOperatedTime(LocalDateTime.now().toString());
        faultOperateInfo.setLinkman(faultInfo.getLinkman());
        faultOperateInfo.setFaultOperateStatus(0);
         */
        //添加到故障操作表中
        //faultOperateInfoMapper.insert(faultOperateInfo);
        return new ResponseData<>(200, "success", "success");
    }


    @PostMapping("/api/faultInfoHandle")
    public ResponseData handlefaultInfo(@RequestBody FaultInfo faultInfo) {
        //自己添加
        //添加到故障信息表中
        //faultInfo.setCreatedTime(LocalDateTime.now().toString());
        faultInfo.setStatus(1);
        faultInfo.setHandledTime(LocalDateTime.now().toString());
        faultInfoMapper.updateById(faultInfo);
        return new ResponseData<>(200, "success", "success");
    }

}
