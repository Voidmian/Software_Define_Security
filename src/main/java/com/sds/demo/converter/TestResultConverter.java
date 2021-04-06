package com.sds.demo.converter;

import com.sds.demo.Entity.BaseList;
import com.sds.demo.Entity.TestResult1;
import com.sds.demo.VO.BaseListVO;
import com.sds.demo.VO.TestResultDetailVO;
import com.sds.demo.VO.TestResultVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;

/**
 * @Author Voidmian
 * @Date 2021/1/28 14:17
 */
public class TestResultConverter {

    static public TestResult1 convertVD(TestResultVO testResultVO) {
        TestResult1 testResult1 = new TestResult1();
        BeanUtils.copyProperties(testResultVO, testResult1);
        return testResult1;
    }

    static public TestResultVO convertDV(TestResult1 testResult1) {
        TestResultVO testResultVO = new TestResultVO();
        BeanUtils.copyProperties(testResult1, testResultVO);
        return testResultVO;
    }

    static public TestResult1 convertDetailVD(TestResultDetailVO testResultDetailVO) {
        TestResult1 testResult1 = new TestResult1();
        BeanUtils.copyProperties(testResultDetailVO, testResult1);
        return testResult1;
    }

    static public TestResultDetailVO convertDDetailV(TestResult1 testResult1) {
        TestResultDetailVO testResultDetailVO = new TestResultDetailVO();
        BeanUtils.copyProperties(testResult1, testResultDetailVO);
        return testResultDetailVO;
    }


    static public BaseListVO<TestResultVO> convertListDV(BaseList<TestResult1> baseList) {
        BaseListVO<TestResultVO> baseListVO = new BaseListVO<>(baseList.getPageSize(), baseList.getPageIndex());
        baseListVO.setList(new ArrayList<>());
        for (TestResult1 c : baseList.getList()
        ) {
            baseListVO.getList().add(convertDV(c));
        }
        return baseListVO;
    }

}
