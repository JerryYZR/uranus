package com.team7.uranus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.team7.uranus.VO.UserRoleView;
import com.team7.uranus.entity.User;
import org.springframework.stereotype.Service;

@Service
public interface UserService extends IService<User> {
    UserRoleView getUserRoleView(Integer userId);
}
