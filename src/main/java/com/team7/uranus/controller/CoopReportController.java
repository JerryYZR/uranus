package com.team7.uranus.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.team7.uranus.domain.ResponseData;
import com.team7.uranus.entity.CoopReport;
import com.team7.uranus.mapper.CoopReportMapper;

@RestController
public class CoopReportController {
	@Autowired
	private CoopReportMapper coopReportMapper;
	
	/**
	 * 主界面显示信息
	 * @param pageNum
	 * @param malstate
	 * @param repName
	 */
	  @GetMapping("/api/coopReport")
	public ResponseData getRep(@RequestParam int pageNum, @RequestParam Integer malState, @RequestParam String malCap) {
	 Page<CoopReport> coopReportPage = new Page<>();
	 coopReportPage.setPages(pageNum);
	 coopReportPage.setSize(10);
	 IPage<CoopReport> page = coopReportMapper.selectPage(coopReportPage,
			 new LambdaQueryWrapper<CoopReport>().like(!malCap.isEmpty(),CoopReport::getMalCap, malCap)
	   .eq(!(malState==null),CoopReport::getMalState, malState));
	 ResponseData coopReportResponseData = new ResponseData<>();
	 coopReportResponseData.setData(page);
	 return coopReportResponseData;
	    }
	  
  
	/**
	 * 
	 * @param coopReport
	 * @return
	 */
	@PostMapping("/api/report")
	public ResponseData postReport(@RequestBody CoopReport coopReport){
		coopReport.setRepTime(LocalDateTime.now().toString());
		coopReport.setMalState(0);
		coopReportMapper.insert(coopReport);
		return new  ResponseData<>(200, "success", "success");
	}
  
  
	/**
	 * 
	 * 根据repid返回结果
	 * 表单
	 * @param repId
	 * @return
	 */
    @GetMapping("/api/report/{repId}")
    public ResponseData getChangeApply(@PathVariable int repId) {
        CoopReport coopReport= coopReportMapper.selectById(repId);
        ResponseData<CoopReport> r = new ResponseData<>();
        r.setData(coopReport);
        return r;
    }

	    
	/**
	 * 
	 * 撤销或者其他更新状态
	 * @param repid
	 * @return
	 */
	
    @PutMapping("/api/reback/{repId}")
    public ResponseData reback(@PathVariable int repId) {
	
	     CoopReport coopReport = coopReportMapper.selectById(repId);
	     coopReport.setMalState(0); 
	     return new ResponseData(200,"success","success");
    }
	
    
    /**
     * 确认报备
     * @param repId
     * @return
     */
    @PutMapping("/api/confirmrep/{repId}")
    public ResponseData confirmrep(@PathVariable int repId) {

	     CoopReport coopReport2 = coopReportMapper.selectById(repId);
	     coopReport2.setMalState(2);
	     return new ResponseData(200,"success","success");

    }
}
