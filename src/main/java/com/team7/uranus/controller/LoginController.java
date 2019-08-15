package com.team7.uranus.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.team7.uranus.Exception.MyException;
import com.team7.uranus.domain.ResponseData;
import com.team7.uranus.entity.User;
import com.team7.uranus.mapper.UserMapper;
import com.team7.uranus.util.Md5Util;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class LoginController {

    @Autowired
    private UserMapper userMapper;

    @PostMapping("/login")
    public ResponseData<Map> login(@RequestBody User user) throws MyException {
        String username = user.getUserName();
        String password = user.getPassword();
        User user1 = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUserName,username).eq(User::getPassword,Md5Util.encode(password)));
        if(user1==null){
            throw new MyException(670,"账号密码错误");
        }
        String token = Jwts.builder().setSubject(""+user1.getUserId())
                .claim("roles", user1.getIsmanager()).setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "secretkey").compact();
        Map map = new HashMap();
        map.put("token",token);
        map.put("roles",user1.getIsmanager());
        ResponseData<Map> r = new ResponseData<>();
        r.setData(map);
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
