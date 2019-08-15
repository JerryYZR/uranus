package com.team7.uranus.controller;


import com.team7.uranus.domain.ResponseData;
import com.team7.uranus.entity.AppInfo;
import com.team7.uranus.mapper.AppInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class AppInfoController {

    @Autowired
    private AppInfoMapper appInfoMapper;

    @PostMapping("/api/appInfo")
    public ResponseData addAppInfo(@RequestBody AppInfo appInfo) {
        appInfoMapper.insert(appInfo);
        return new ResponseData<>(200, "success", "success");
    }

    @GetMapping("/api/appInfo/{appId}")
    public ResponseData deleteAppInfo(@PathVariable int appId) {
        appInfoMapper.deleteById(appId);
        return new ResponseData<>(200, "success", "success");
    }

    @GetMapping("/api/getInfo/{appId}")
    public ResponseData getAppInfo(@PathVariable int appId) {
        AppInfo appInfo = appInfoMapper.selectById(appId);
        ResponseData<AppInfo> r = new ResponseData<>();
        r.setData(appInfo);
        return r;
    }


    @PutMapping("/api/appInfo/{appId}")
    public ResponseData updateAppInfo(@RequestBody AppInfo appInfo) {
        appInfoMapper.updateById(appInfo);
        return new ResponseData<>(200, "success", "success");
    }
}
