package com.team7.uranus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team7.uranus.entity.OrgInfo;
import com.team7.uranus.mapper.OrgInfoMapper;
import com.team7.uranus.service.OrgInfoService;
import org.springframework.stereotype.Service;

@Service
public class OrgInfoServiceImpl extends ServiceImpl<OrgInfoMapper, OrgInfo> implements OrgInfoService {
}
