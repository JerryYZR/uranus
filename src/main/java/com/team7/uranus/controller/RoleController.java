package com.team7.uranus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team7.uranus.Exception.MyException;
import com.team7.uranus.domain.ResponseData;
import com.team7.uranus.entity.RoleInfo;
import com.team7.uranus.entity.RoleUser;
import com.team7.uranus.entity.User;
import com.team7.uranus.entity.UserSample;
import com.team7.uranus.mapper.RoleMapper;
import com.team7.uranus.mapper.RoleUserMapper;
import com.team7.uranus.mapper.UserMapper;
import com.team7.uranus.mapper.UserSampleMapper;
import com.team7.uranus.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class RoleController {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleUserMapper roleUserMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserSampleMapper userSampleMapper;

    @Autowired
    private UserService userService;

    @GetMapping("/api/role")
    public ResponseData getAllRole(){
        List<RoleInfo> roleInfoList = roleMapper.selectList(null);
        ResponseData<List> responseData = new ResponseData<>();
        responseData.setData(roleInfoList);
        return responseData;
    }

    @PostMapping("/api/admin/addRole")
    public ResponseData addRole(@RequestBody Map<String,Integer> map){
        Integer roleId = map.get("roleId");
        Integer userId = map.get("userId");
        RoleUser roleUser = new RoleUser();
        roleUser.setRoleId(roleId);
        roleUser.setUserId(userId);
        roleUserMapper.insert(roleUser);
        return new ResponseData<>(200, "true", "hello");
    }


    @GetMapping("/api/myRole")
    public ResponseData getUserRole(@RequestAttribute("userId")Integer userId){
        ResponseData r = new ResponseData();
        r.setData(userService.getUserRoleView(userId));
        return r;
    }

    @GetMapping("/api/otherRole/{userId}")
    public ResponseData getOtherRole(@PathVariable Integer userId){
        ResponseData r = new ResponseData();
        r.setData(userService.getUserRoleView(userId));
        return r;
    }

    @PostMapping("/api/reaRole")
    public ResponseData getOtherRole(@RequestBody Map<String,String> map){
        String userName = map.get("userName");
        List<UserSample> userSampleList = userSampleMapper.selectList(new QueryWrapper<UserSample>()
                .lambda().like(!userName.isEmpty(),UserSample::getUserName,userName));
        ResponseData r = new ResponseData();
        r.setData(userSampleList);
        return r;
    }

    @DeleteMapping("/api/admin/delRole/{userId}/{roleId}")
    public  ResponseData delUserRole(@PathVariable Integer userId,@PathVariable Integer roleId){
        RoleUser roleUser = new RoleUser();
        roleUser.setRoleId(roleId);
        roleUser.setUserId(userId);
        int resultId = roleUserMapper.delete(new QueryWrapper<RoleUser>().lambda().eq(RoleUser::getRoleId,roleId).eq(RoleUser::getUserId,userId));
        if (resultId!=1){
            throw new MyException(692,"没有对应数据");
        }
        return new ResponseData<>(200, "true", "success");
    }
}
