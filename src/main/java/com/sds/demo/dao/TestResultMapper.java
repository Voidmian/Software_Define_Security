package com.sds.demo.dao;

import com.sds.demo.Entity.Data;
import com.sds.demo.Entity.TestCase;
import com.sds.demo.Entity.TestResult;


import java.util.List;

/**
 * @Author Voidmian
 * @Date 2021/1/28 16:08
 */
@org.springframework.stereotype.Component
public interface TestResultMapper {
    TestResult getOneById(int id);
    List<TestResult> getListByCaseIdPage(int id,int pageSize, int offset);
    List<TestResult> getListByCaseId(int id);
    int insertTestResult(Data data);
    int deleteTestResult(int id);
}
