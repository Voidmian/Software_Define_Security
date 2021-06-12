package com.sds.demo.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sds.demo.Entity.DataD;
import com.sds.demo.Entity.TestResult;
import com.sds.demo.dao.TestResultMapper;
import com.sds.demo.util.TimeUtil;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName: TestResultImplTest
 * @Description: TODO
 * @author: XQC
 * @date: 2021/4/5  12:29
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestResultImplTest extends TestCase {

    @Autowired
    TestResultMapper testResultMapper;

    public void testGetListByCaseName() {
    }
    @Test
    public void testInsert() {
        TestResult data = new TestResult("test");
        data.setBandwidth("10");
        data.setCwnd("10");
        data.setIntercal("10");
        data.setJitter("10");
        data.setLost_total_datagrams("10");
        data.setRetr("10");
        data.setProtocol("tcp");
        data.setTotal_datagrams("10");
        DataD d = new DataD(data.getCaseName(), JSON.toJSONString(data, SerializerFeature.WriteMapNullValue));
        d.setDate(TimeUtil.now());
        testResultMapper.insertTestResult(d);
    }

    public void testDeleteAllByCaseName() {
    }

    public void testGetAll() {
    }
}