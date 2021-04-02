package com.sds.demo.Entity;

import com.sds.demo.dao.TestCaseMapper;
import com.sds.demo.util.TimeUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @Author Voidmian
 * @Date 2021/1/28 14:50
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCaseTest {
    @Autowired
    private TestCaseMapper testCaseMapper;

    @Test
   public void insert(){
        TestCase testCase = new TestCase();
        testCase.setId(1);
        testCase.setName("aaa");
        testCase.setDesc("测试");
        //testCase.setConcurrency(10);
        //testCase.setSeconds(100);
        testCase.setProtocol("tcp");
        testCase.setCreateTime(TimeUtil.now());
        testCase.setUpdateTime(TimeUtil.now());
        int res = testCaseMapper.insertTestCase(testCase);
        System.out.println(res);
    }
    @Test
    public void update(){
        TestCase testCase = new TestCase();
        testCase.setId(1);
        testCase.setName("bbb");
        //testCase.setConcurrency(17667);
        //testCase.setSeconds(100);
        testCase.setProtocol("tcp");
        testCase.setCreateTime(TimeUtil.now());
        testCase.setUpdateTime(TimeUtil.now());
        int res = testCaseMapper.updateTestCase(testCase);
        System.out.println(res);
    }

    @Test
    public void delete(){
        int res = testCaseMapper.deleteTestCase(1);
        System.out.println(res);
    }

/*    @Test
    public void find(){
        TestCase testCase = testCaseMapper.getOneByName();
        List<TestCase> res = testCaseMapper.getAll();
        System.out.println(res.size());
        res = testCaseMapper.getAllPage(5, 1);
        System.out.println(res.size());
        res = testCaseMapper.getListByName("aaa");
        System.out.println(res.size());

    }*/


}