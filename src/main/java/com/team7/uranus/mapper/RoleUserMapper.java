package com.team7.uranus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.team7.uranus.entity.RoleInfo;
import com.team7.uranus.entity.RoleUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface RoleUserMapper extends BaseMapper<RoleUser> {

    @Select("SELECT role_info.* FROM role_info,role_user WHERE role_user.user_id = #{userId} AND role_user.role_id=role_info.role_id")
    List<RoleInfo> getRoleInfoList(Integer userId);

}
