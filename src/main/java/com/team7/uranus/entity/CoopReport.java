package com.team7.uranus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import lombok.Data;

@Data
@TableName("report_info")
public class CoopReport {
	@TableId
	private Integer repId;
	private String malCap;
	private int malState;
	private String contact;
	private String repTime;
	private String hopeTime;
	private String tel;
	private String content;
	private int userId;
	
}
