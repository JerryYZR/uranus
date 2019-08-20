package com.team7.uranus.controller;

import java.time.LocalDateTime;

import com.team7.uranus.Exception.MyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     *
     * @param pageNum
     * @param malstate
     * @param repName
     */
    @GetMapping("/api/coopReport")
    public ResponseData getRep(@RequestParam int pageNum, @RequestParam Integer malState, @RequestParam String malCap,@RequestAttribute(value="userId")int userId,@RequestAttribute(value="roles")int roles) {
        Page<CoopReport> coopReportPage = new Page<>(pageNum,10);
        IPage<CoopReport> page = coopReportMapper.selectPage(coopReportPage,
                new LambdaQueryWrapper<CoopReport>().eq(roles>0?false:true,CoopReport::getUserId,userId).like(!malCap.isEmpty(), CoopReport::getMalCap, malCap)
                        .eq(!(malState == null), CoopReport::getMalState, malState));
        ResponseData coopReportResponseData = new ResponseData<>();
        coopReportResponseData.setData(page);
        return coopReportResponseData;
    }


    /**
     * @param coopReport
     * @return
     */
    @PostMapping("/api/insertcoop")
    public ResponseData postReport(@RequestBody CoopReport coopReport,@RequestAttribute(value="userId")int userId) {
        coopReport.setRepTime(LocalDateTime.now().toString());
        coopReport.setMalState(0);
        coopReport.setUserId(userId);
        coopReportMapper.insert(coopReport);
        return new ResponseData<>(200, "success", "success");
    }


    /**
     * 根据repid返回结果
     * 表单1
     * @param repId
     * @return
     */
    @GetMapping("/api/report/{repid}")
    public ResponseData getChangeApply(@PathVariable int repid) {
        CoopReport coopReport = coopReportMapper.selectById(repid);
        ResponseData<CoopReport> r = new ResponseData<>();
        r.setData(coopReport);
        return r;
    }


    /**
     * 撤销或者其他更新状态
     *1
     * @param repid
     * @return
     */

    @PutMapping("/api/reback/{repId}")
    public ResponseData reback(@PathVariable int repId,@RequestAttribute(value="userId")int userId) {

        CoopReport coopReport = coopReportMapper.selectById(repId);

        if (coopReport.getUserId() == userId) {
            coopReport.setMalState(1);
            coopReportMapper.updateById(coopReport);
            return new ResponseData(200, "success", "success");
        } else {
            throw new MyException(797, "无法撤销");
        }
    }


    /**
     * 确认报备
     *0
     * @param repId
     * @return
     */

    @PutMapping("/api/admin/confirmrep/{repId}")
    public ResponseData confirmrep(@PathVariable int repId) {
        CoopReport coopReport= coopReportMapper.selectById(repId);
        coopReport.setMalState(2);
        coopReportMapper.updateById(coopReport);
        return new ResponseData(200, "success", "success");
    }
}
