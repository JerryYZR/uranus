package com.team7.uranus.controller;

import com.team7.uranus.domain.ResponseData;
import com.team7.uranus.entity.User;
import com.team7.uranus.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/hello")
    public ResponseData<String> hello() {
        List<User> userList = userMapper.selectList(null);
        userList.forEach(System.out::println);
        return new ResponseData<>(200, "true", "hello");
    }

}
