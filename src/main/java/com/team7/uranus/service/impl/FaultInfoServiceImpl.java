package com.team7.uranus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.team7.uranus.entity.FaultInfo;
import com.team7.uranus.mapper.FaultInfoMapper;
import com.team7.uranus.service.FaultInfoService;
import org.springframework.stereotype.Service;

@Service
public class FaultInfoServiceImpl extends ServiceImpl<FaultInfoMapper, FaultInfo> implements FaultInfoService {

}
