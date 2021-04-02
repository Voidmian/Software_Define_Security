package com.sds.demo.dao;

import com.sds.demo.Entity.Component;
import com.sds.demo.Entity.TestCase;

import java.util.List;

/**
 * @Author Voidmian
 * @Date 2021/1/28 14:25
 */
@org.springframework.stereotype.Component
public interface TestCaseMapper {
    TestCase getOneByName(String name);
    List<TestCase> getListByName(String name);
    List<TestCase> getAll();
    List<TestCase> getAllPage(int pageSize, int offset);
    int insertTestCase(TestCase testCase);
    int updateTestCase(TestCase testCase);
    int deleteTestCase(int id);
}
