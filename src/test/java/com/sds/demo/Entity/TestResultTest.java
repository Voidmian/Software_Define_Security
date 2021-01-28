package com.sds.demo.Entity;


import com.sds.demo.VO.TestResultDetailVO;
import com.sds.demo.converter.TestResultConverter;
import com.sds.demo.dao.TestResultMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author Voidmian
 * @Date 2021/1/28 16:30
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestResultTest {
    @Autowired
    private TestResultMapper testResultMapper;

    @Test
    public void insert() {
        int[][] a = {{1, 2}, {3, 4}, {4, 5}};
        int[][] b = {{1, 2}, {3, 4}, {4, 5}};
        TestResultDetailVO testResultDetailVO = new TestResultDetailVO(2,a, b);
        new TestResultConverter();
        TestResult testResult = TestResultConverter.convertDetailVD(testResultDetailVO);
        int res = testResultMapper.insertTestResult(testResult);
        System.out.println(res);
    }

    @Test
    public void delete() {
        int res = testResultMapper.deleteTestResult(1);
        System.out.println(res);
    }

    @Test
    public void find() {
        TestResult testResult = testResultMapper.getOneById(2);
        List   <TestResult> res = testResultMapper.getListByCaseIdPage(1,5, 0);
        System.out.println(res.size());
    }
}