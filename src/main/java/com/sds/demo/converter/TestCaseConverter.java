package com.sds.demo.converter;

import com.sds.demo.Entity.BaseList;
import com.sds.demo.Entity.Component;
import com.sds.demo.Entity.TestCase;
import com.sds.demo.VO.BaseListVO;
import com.sds.demo.VO.ComponentVO;
import com.sds.demo.VO.TestCaseVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;

/**
 * @Author Voidmian
 * @Date 2021/1/28 16:54
 */
 public class TestCaseConverter {
    static  public TestCase convertVD(TestCaseVO testCaseVO){
        TestCase testCase = new TestCase();
        BeanUtils.copyProperties(testCaseVO, testCase);
        return testCase;
    }

    static  public TestCaseVO convertDV(TestCase testCase){
        TestCaseVO testCaseVO = new TestCaseVO();
        BeanUtils.copyProperties(testCase, testCaseVO);
        return testCaseVO;
    }

    static public BaseListVO<TestCaseVO> convertListDV(BaseList<TestCase> baseList) {
        BaseListVO<TestCaseVO> baseListVO = new BaseListVO<>(baseList.getPageSize(), baseList.getPageIndex());
        baseListVO.setList(new ArrayList<>());
        for (TestCase c:baseList.getList()
        ) {
            baseListVO.getList().add(convertDV(c));
        }
        return baseListVO;
    }
}
