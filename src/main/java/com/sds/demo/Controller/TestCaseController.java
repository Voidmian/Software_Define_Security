package com.sds.demo.Controller;

import com.alibaba.fastjson.JSONObject;
import com.sds.demo.Entity.TestCase;
import com.sds.demo.Service.Impl.TestCaseImpl;
import com.sds.demo.Service.Impl.TestResultImpl;
import com.sds.demo.Service.TestCaseService;
import com.sds.demo.Service.TestResultService;
import com.sds.demo.VO.*;
import com.sds.demo.dao.TestCaseMapper;
import com.sds.demo.form.TestCaseForm;
import com.sds.demo.util.TimeUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Voidmian
 * @Date 2021/1/28 16:44
 */
@RestController
@RequestMapping("/test_case")
public class TestCaseController {
    @Autowired
    TestCaseImpl testCaseService;
    @Autowired
    TestResultImpl testResultService;
    @Autowired
    TestCaseMapper testCaseMapper;


    @GetMapping("/get_one/{name}")
    public TestCase getOneByCaseName(@PathVariable String name) {
        TestCase testCase = testCaseMapper.getOneByName(name);
        return testCase;
    }


    @GetMapping("/get_all")
    public List<TestCase> getAllTestCase() {
        List<TestCase> baseList = testCaseService.getAll();
        return baseList;
    }

    @PostMapping("/insert")
    public BaseVO<String> insertTestCase(@RequestBody TestCaseForm testCaseForm) {
        TestCaseVO testCaseVO = new TestCaseVO();

        testCaseVO.setName(testCaseForm.getName());
        testCaseVO.setDesc(testCaseForm.getDesc());
        testCaseVO.setComponentName(testCaseForm.getComponentName());
        testCaseVO.setProtocol(testCaseForm.getProtocol());
        testCaseVO.setTimeSlot(testCaseForm.getTimeSlot());
        testCaseVO.setTotalTime(testCaseForm.getTotalTime());
        testCaseVO.setBandwidthLimit(testCaseForm.getBandwidthLimit());
        testCaseVO.setBidirectionalTest(testCaseForm.getBidirectionalTest());
        testCaseVO.setTotalPackages(testCaseForm.getTotalPackages());
        testCaseVO.setBufferLength(testCaseForm.getBufferLength());
        testCaseVO.setTCPWindow(testCaseForm.getTCPWindow());
        testCaseVO.setMss(testCaseForm.getMss());
        testCaseVO.setIpv4_6(testCaseForm.getIpv4_6());

        testCaseVO.setCreateTime(TimeUtil.now());
        testCaseVO.setUpdateTime(TimeUtil.now());

        String res = testCaseService.insert(testCaseVO);

        return new BaseVO<>("success", res, 200);
    }


    @DeleteMapping("/delete")
    /**
     * @Param id int
     */
    public BaseVO<String> deleteTestCase(int id ) {
        String res = testCaseService.delete(id);
        return new BaseVO<>("success", res, 200);
    }

    @PostMapping("/start")
    public BaseVO<String> startTestCase(@RequestBody JSONObject name) {

        testCaseService.startCase(name.getString("name"));
        //: TODO 重写
        return new BaseVO<>("success", null, 200);
    }

}
