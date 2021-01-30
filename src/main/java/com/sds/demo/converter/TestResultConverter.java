package com.sds.demo.converter;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.sds.demo.Entity.BaseList;
import com.sds.demo.Entity.TestCase;
import com.sds.demo.Entity.TestResult;
import com.sds.demo.VO.BaseListVO;
import com.sds.demo.VO.TestCaseVO;
import com.sds.demo.VO.TestResultDetailVO;
import com.sds.demo.VO.TestResultVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;

/**
 * @Author Voidmian
 * @Date 2021/1/28 14:17
 */
public class TestResultConverter {

    static public TestResult convertVD(TestResultVO testResultVO) {
        TestResult testResult = new TestResult();
        BeanUtils.copyProperties(testResultVO, testResult);
        return testResult;
    }

    static public TestResultVO convertDV(TestResult testResult) {
        TestResultVO testResultVO = new TestResultVO();
        BeanUtils.copyProperties(testResult, testResultVO);
        return testResultVO;
    }

    static public TestResult convertDetailVD(TestResultDetailVO testResultDetailVO) {
        TestResult testResult = new TestResult();
        BeanUtils.copyProperties(testResultDetailVO, testResult);
        return testResult;
    }

    static public TestResultDetailVO convertDDetailV(TestResult testResult) {
        TestResultDetailVO testResultDetailVO = new TestResultDetailVO();
        BeanUtils.copyProperties(testResult, testResultDetailVO);
        return testResultDetailVO;
    }


    static public BaseListVO<TestResultVO> convertListDV(BaseList<TestResult> baseList) {
        BaseListVO<TestResultVO> baseListVO = new BaseListVO<>(baseList.getPageSize(), baseList.getPageIndex());
        baseListVO.setList(new ArrayList<>());
        for (TestResult c : baseList.getList()
        ) {
            baseListVO.getList().add(convertDV(c));
        }
        return baseListVO;
    }

}
