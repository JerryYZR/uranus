package com.team7.uranus.VO;

import com.baomidou.mybatisplus.annotation.TableId;
import com.team7.uranus.entity.RoleInfo;
import lombok.Data;

import java.util.List;

@Data
public class UserRoleView {
    private int userId;
    private String userName;
    private String nickname;
    private String gender;
    private String email;
    private String addr;
    private String office;
    private String department;
    private String position;
    private Integer ismanager;
    private String telphone;
    private List<RoleInfo> roleInfoList;
}
