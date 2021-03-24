package com.sds.demo.Controller;

import com.sds.demo.Entity.BaseList;
import com.sds.demo.Entity.TestCase;
import com.sds.demo.Entity.TestResult;
import com.sds.demo.Service.TestCaseService;
import com.sds.demo.Service.TestResultService;
import com.sds.demo.VO.*;
import com.sds.demo.form.TestCaseForm;
import com.sds.demo.util.TimeUtil;
import org.apache.ibatis.annotations.Delete;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Voidmian
 * @Date 2021/1/28 16:44
 */
@RestController
@RequestMapping("/test_case")
public class TestCaseController {
    TestCaseService testCaseService;
    TestResultService testResultService;
    public TestCaseController( TestCaseService testCaseService, TestResultService testResultService) {
        this.testCaseService = testCaseService;
        this.testResultService = testResultService;
    }


    @GetMapping("/get_all")
    public BaseVO<BaseListVO> getAllTestCase(
            @RequestParam(value = "page_size", defaultValue = "100",required = false) int pageSize,
            @RequestParam(value = "page_index", defaultValue = "0",required = false) int pageIndex) {
        BaseListVO<TestCaseVO> baseList = testCaseService.getAllComponentPage(pageSize, pageIndex);
        return new BaseVO<>("success", baseList, 200);
    }

    @PostMapping("/insert")
    public BaseVO<String> insertTestCase(@RequestBody TestCaseForm testCaseForm) {
        TestCaseVO testCaseVO = new TestCaseVO();
        testCaseVO.setName(testCaseForm.getName());
        testCaseVO.setDesc(testCaseForm.getDesc());
        testCaseVO.setConcurrency(testCaseForm.getConcurrency());
        testCaseVO.setProtocol(testCaseForm.getProtocol());
        testCaseVO.setSeconds(testCaseForm.getSeconds());
        testCaseVO.setCreateTime(TimeUtil.now());
        testCaseVO.setUpdateTime(TimeUtil.now());
        String res = testCaseService.insert(testCaseVO);
        return new BaseVO<>("success", res, 200);
    }

    @PostMapping("/update")
    public BaseVO<String> updateTestCase(@RequestBody TestCaseForm testCaseForm) {
        TestCaseVO testCaseVO = new TestCaseVO();
        testCaseVO.setId(testCaseForm.getId());
        testCaseVO.setName(testCaseForm.getName());
        testCaseVO.setDesc(testCaseForm.getDesc());
        testCaseVO.setConcurrency(testCaseForm.getConcurrency());
        testCaseVO.setProtocol(testCaseForm.getProtocol());
        testCaseVO.setSeconds(testCaseForm.getSeconds());
        testCaseVO.setUpdateTime(TimeUtil.now());
        String res = testCaseService.update(testCaseVO);
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
    /**
     * @Param id int
     */
    public BaseVO<String> startTestCase(int id) {
        TestResultDetailVO testResultDetailVO = testCaseService.startCase(id);
        String res = testResultService.insert(testResultDetailVO);
        return new BaseVO<>("success", res, 200);
    }




}
