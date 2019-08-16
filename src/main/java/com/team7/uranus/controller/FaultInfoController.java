package com.team7.uranus.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team7.uranus.domain.ResponseData;
import com.team7.uranus.entity.FaultInfo;
import com.team7.uranus.mapper.FaultInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;


@RestController
public class FaultInfoController {

    @Autowired
    private FaultInfoMapper faultInfoMapper;

    //管理员根据条件查询,(status,faultApplication,faultTitle)为查询的3个条件，其中前两个是精确查询，最后一个是模糊查询。
    @GetMapping("/api/admin/faultInfo")
    public ResponseData getFaultInfo(@RequestParam Integer pageNum,
                                     @RequestParam Integer status,
                                     @RequestParam String faultApplication,
                                     @RequestParam String faultTitle) { //状态，应用，故障标题
        Page<FaultInfo> faultInfoPage = new Page<>(pageNum,10);
        IPage<FaultInfo> page = faultInfoMapper.selectPage(faultInfoPage, Wrappers.<FaultInfo>query().lambda()
                .eq(status != null, FaultInfo::getStatus, status).eq(!faultApplication.isEmpty(), FaultInfo::getFaultApplication, faultApplication)
                .like(faultTitle != null, FaultInfo::getFaultTitle, faultTitle));
        ResponseData faultInfoResponseData = new ResponseData<>();
        faultInfoResponseData.setData(page);
        return faultInfoResponseData;
    }


    //普通用户查看自己提交的故障单
    @GetMapping("/api/userfaultInfo")
    public ResponseData getUserFaultInfo(@RequestParam Integer pageNum,
                                         @RequestAttribute(value="userId")Integer linkUserId,
                                         @RequestParam Integer status,
                                         @RequestParam String faultApplication,
                                         @RequestParam String faultTitle) {
        Page<FaultInfo> faultInfoPage = new Page<>(pageNum,10);
        IPage<FaultInfo> page = faultInfoMapper.selectPage(faultInfoPage, Wrappers.<FaultInfo>query().lambda()
                .eq(FaultInfo::getLinkUserId, linkUserId)
                .eq(status!=null,FaultInfo::getStatus, status)
                .eq(!faultApplication.isEmpty(),FaultInfo::getFaultApplication, faultApplication)
                .eq(!faultTitle.isEmpty(),FaultInfo::getFaultTitle, faultTitle));
        ResponseData faultInfoResponseData = new ResponseData<>();
        faultInfoResponseData.setData(page);
        return faultInfoResponseData;
    }




    //详细页，点击对应数据进入详细信息界面，根据传回的faultId得到对应故障单。
    @GetMapping("/api/faultInfoDet/{faultId}")
    public ResponseData<FaultInfo> getFaultAndOperateInfo(@PathVariable int faultId) {
        FaultInfo faultInfo = faultInfoMapper.selectById(faultId);
        ResponseData<FaultInfo> faultInfoResponse = new ResponseData<>();
        faultInfoResponse.setData(faultInfo);
        return faultInfoResponse;
    }


    //普通用户提交故障单
    //用户的id：linkUserId，由后端读取前端的数据包获取
    //故障状态：Status，由后端默认填写
    //故障提交时间：SubmitedTime，由后端获取当前时间
    @PostMapping("/api/faultInfoSub")
    public ResponseData addfaultInfo(@RequestBody FaultInfo faultInfo,@RequestAttribute(value="userId")Integer linkUserId) {

        faultInfo.setLinkUserId(linkUserId);
        faultInfo.setStatus(0);
        faultInfo.setSubmitedTime(LocalDateTime.now().toString());
        faultInfoMapper.insert(faultInfo);
        return new ResponseData<>(200, "success", "success");
    }





    //管理员处理故障
    @PostMapping("/api/admin/faultInfoHandle")
    public ResponseData handlefaultInfo(@RequestBody FaultInfo faultInfo) {
        faultInfo.setStatus(1);
        faultInfo.setHandledTime(LocalDateTime.now().toString());
        faultInfoMapper.updateById(faultInfo);
        return new ResponseData<>(200, "success", "success");
    }

}
