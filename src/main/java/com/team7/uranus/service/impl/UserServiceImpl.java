package com.team7.uranus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team7.uranus.VO.UserRoleView;
import com.team7.uranus.entity.RoleInfo;
import com.team7.uranus.entity.User;
import com.team7.uranus.mapper.RoleUserMapper;
import com.team7.uranus.mapper.UserMapper;
import com.team7.uranus.service.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService  {
    @Autowired
    UserMapper userMapper;

    @Autowired
    RoleUserMapper roleUserMapper;

    @Override
    public UserRoleView getUserRoleView(Integer userId) {
        User user = userMapper.selectOne(new QueryWrapper<User>().lambda().eq(User::getUserId,userId));
        UserRoleView userRoleView = new UserRoleView();
        BeanUtils.copyProperties(user,userRoleView);
        List<RoleInfo> roleInfos = roleUserMapper.getRoleInfoList(userId);
        userRoleView.setRoleInfoList(roleInfos);
        return userRoleView;
    }
}
