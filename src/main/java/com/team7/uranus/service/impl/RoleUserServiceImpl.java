package com.team7.uranus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team7.uranus.entity.RoleUser;
import com.team7.uranus.mapper.RoleUserMapper;
import com.team7.uranus.service.RoleUserService;
import org.springframework.stereotype.Service;

@Service
public class RoleUserServiceImpl extends ServiceImpl<RoleUserMapper, RoleUser> implements RoleUserService {

}
