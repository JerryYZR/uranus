package com.team7.uranus.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team7.uranus.Exception.MyException;
import com.team7.uranus.domain.ResponseData;
import com.team7.uranus.entity.AppInfo;
import com.team7.uranus.entity.User;
import com.team7.uranus.mapper.AppInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@RestController
public class AppInfoController {

    @Autowired
    private AppInfoMapper appInfoMapper;

    //显示应用信息
    @PostMapping("/api/getInfo")
    public ResponseData getRep(@RequestParam int pageNum, @RequestParam String department, @RequestParam String enName) {
        Page<AppInfo> appInfoPage = new Page<>();
        appInfoPage.setPages(pageNum);
        appInfoPage.setSize(10);
        IPage<AppInfo> page = appInfoMapper.selectPage(appInfoPage,
                new LambdaQueryWrapper<AppInfo>().like(!enName.isEmpty(), AppInfo::getEnName, enName)
                        .eq(!department.isEmpty(), AppInfo::getDepartment, department));
        ResponseData appInfoResponseData = new ResponseData<>();
        appInfoResponseData.setData(page);
        return appInfoResponseData;
    }



    //增加应用信息
    @PostMapping("/api/appInfo")
    public ResponseData addAppInfo(@RequestBody AppInfo appInfo, @RequestAttribute(value = "userId")Integer userId) {
        appInfo.setUpdateTime(LocalDateTime.now().toString());
        appInfo.setAppApplyId(userId);
        appInfoMapper.insert(appInfo);
        return new ResponseData<>(200, "success", "success");
    }

    //删除应用信息
    @GetMapping("/api/appInfo/{appId}")
    public ResponseData deleteAppInfo(@PathVariable int appId, @RequestAttribute(value = "userId") Integer userId, @RequestAttribute(value = "roles") Integer roles) {
        AppInfo appInfo = appInfoMapper.selectById(appId);

        if ((userId != appInfo.getAppApplyId()) && (roles != 1)) {
            throw new MyException(797, "该用户没有权限删除该应用");
        }
        Integer isdelete = appInfoMapper.deleteById(appId);
        if (isdelete == 1) {
            return new ResponseData<>(200, "success", "success");
        } else {
            throw new MyException(798, "删除失败");
        }
    }

    //得到应用详细信息
    @GetMapping("/api/getInfo/{appId}")
    public ResponseData getAppInfo(@PathVariable int appId) {
        AppInfo appInfo = appInfoMapper.selectById(appId);
        ResponseData<AppInfo> r = new ResponseData<>();
        r.setData(appInfo);
        return r;
    }

    //更新
    @PutMapping("/api/appInfo")
    public ResponseData updateAppInfo(@RequestBody AppInfo appInfo, @RequestAttribute(value = "userId") Integer userId, @RequestAttribute(value = "roles") Integer roles) {

        if ((userId != appInfo.getAppApplyId()) && (roles != 1)) {
            throw new MyException(797, "该用户没有权限更新该应用");
        }
        appInfoMapper.updateById(appInfo);
        return new ResponseData<>(200, "success", "success");
    }
}
