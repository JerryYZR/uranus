package com.team7.uranus.controller;

import com.team7.uranus.domain.ResponseData;
import com.team7.uranus.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class HelloController {

    @Autowired
    private UserMapper userMapper;

    @RequestMapping("/api/hello")
    public ResponseData<String> hello(@RequestAttribute(value="userId")String userId, @RequestAttribute(value="roles")String roles) {
        log.info(userId);
        log.info(roles);
        return new ResponseData<>(200, "true", "hello");
    }

}
