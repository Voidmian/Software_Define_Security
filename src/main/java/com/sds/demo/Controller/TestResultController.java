package com.sds.demo.Controller;

import com.sds.demo.Entity.TestResult;
import com.sds.demo.Service.TestCaseService;
import com.sds.demo.Service.TestResultService;
import com.sds.demo.VO.*;
import com.sds.demo.form.TestCaseForm;
import com.sds.demo.util.TimeUtil;
import org.springframework.web.bind.annotation.*;

/**
 * @Author Voidmian
 * @Date 2021/1/28 17:49
 */
@RestController
@RequestMapping("/test_result")
public class TestResultController {
    TestResultService testResultService;
    public TestResultController( TestResultService testResultService) {
        this.testResultService = testResultService;
    }


    @GetMapping("/get_all")
    public BaseVO<BaseListVO> getAllTestCase(
            @RequestParam(value = "case_id" ,required = true) Integer caseId,
            @RequestParam(value = "page_size", defaultValue = "100",required = false) int pageSize,
            @RequestParam(value = "page_index", defaultValue = "0",required = false) int pageIndex) {
        BaseListVO<TestResultVO> baseList = testResultService.getAllTestResultPageByCaseId(caseId,pageSize, pageIndex);
        return new BaseVO<>("success", baseList, 200);
    }

    @DeleteMapping("/delete_all")

    public BaseVO<String> deleteTestCase( @RequestParam(value = "case_id" ,required = true) Integer caseId) {
        Integer res = testResultService.deleteAllById(caseId);
        return new BaseVO<>("success", "删除"+res+"条记录", 200);
    }

    @GetMapping("/get_one")
    /**
     * @Param id int
     */
    public BaseVO<TestResultDetailVO> getOneTestCase(int id) {
        TestResultDetailVO TestResultDetailVO = testResultService.getTestResultById(id);
        return new BaseVO<>("success", TestResultDetailVO, 200);
    }

    @DeleteMapping("/delete")
    /**
     * @Param id int
     */
    public BaseVO<String> deleteTestCase(int id ) {
        String res = testResultService.delete(id);
        return new BaseVO<>("success", res, 200);
    }

}

