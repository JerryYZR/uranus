package com.team7.uranus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team7.uranus.Exception.MyException;
import com.team7.uranus.domain.ResponseData;
import com.team7.uranus.entity.User;
import com.team7.uranus.mapper.UserMapper;
import com.team7.uranus.util.JwtUtil;
import com.team7.uranus.util.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public ResponseData<String> login(@RequestBody User user) throws MyException {
        String username = user.getUserName();
        String password = user.getPassword();
        String jwtToken = JwtUtil.generateToken("123", 456);
        ResponseData<String> r = new ResponseData<>();
        r.setData(jwtToken);
        return r;
    }

    @PostMapping("/register")
    public ResponseData<String> register(@RequestBody User user) {
        String name = user.getUserName();
        int userListSize = userMapper.selectList(new QueryWrapper<User>().lambda().eq(User::getUserName, name)).size();
        if (userListSize > 0) {
            throw new MyException(666, "用户已经注册");
        }
        String newPassword = Md5Util.encode(user.getPassword());
        user.setIsmanager(0);
        user.setPassword(newPassword);
        userMapper.insert(user);
        return new ResponseData<>(200, "success", "success");
    }
}
