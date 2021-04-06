package com.sds.demo.Controller;

import com.sds.demo.Entity.TestResult;
import com.sds.demo.Entity.DataD;
import com.sds.demo.Entity.TestResult1;
import com.sds.demo.Service.TestResultService;
import com.sds.demo.VO.*;
import com.sds.demo.converter.JSON2Data;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author Voidmian
 * @Date 2021/1/28 17:49
 */
@RestController
@RequestMapping("/test_result")
public class TestResultController {
    TestResultService testResultService;
    public TestResultController(TestResultService testResultService) {
        this.testResultService = testResultService;
    }

    @DeleteMapping("/delete_all")
    public BaseVO<String> deleteTestCase( @RequestParam(value = "case_name", required = true) String caseName) {
        Integer res = testResultService.deleteAllByCaseName(caseName);
        return new BaseVO<>("success", "删除"+res+"条记录", 200);
    }

    @GetMapping("/get_one")
    /**
     * @Param String name
     */
    public BaseVO<List<TestResult1>> getListByCaseName(String name) {
        List<DataD> list = testResultService.getListByCaseName(name);
        JSON2Data J2D = new JSON2Data();
        List<TestResult1> TestResultDetailVO = J2D.convert(list);
        return new BaseVO<>("success", TestResultDetailVO, 200);
    }

    @GetMapping("/get_all")
    public BaseVO<List<TestResult1>> getAll() {
        List<DataD> list = testResultService.getAll();
        JSON2Data J2D = new JSON2Data();
        List<TestResult1> TestResultDetailVO = J2D.convert(list);
        return new BaseVO<>("success", TestResultDetailVO, 200);
    }
}

