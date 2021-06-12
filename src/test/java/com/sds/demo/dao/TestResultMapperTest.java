package com.sds.demo.dao;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;

import com.sds.demo.Entity.DataD;
import com.sds.demo.Entity.TestResult;
import com.sds.demo.Entity.TestResult1;
import com.sds.demo.converter.JSON2Data;
import com.sds.demo.util.TimeUtil;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

/**
 * @ClassName: TestResultMapperTest
 * @Description: TODO
 * @author: XQC
 * @date: 2021/4/3  15:17
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestResultMapperTest extends TestCase {
    @Autowired
    private TestResultMapper testResultMapper;

    private JSON2Data j2d = new JSON2Data();

    @Test
    public void testGetListByCaseIdPage() {
        System.out.println(testResultMapper.getListByCaseName("test"));
    }
    @Test
    public void testGetListByCaseName() {
        List<DataD> test = testResultMapper.getListByCaseName("test");
        System.out.println(test);
    }
    @Test
    public void testInsertTestResult() {
        TestResult data = new TestResult("test");
        data.setProtocol("tcp");
        data.setIntercal("10");
        data.setTransfer("10");
        data.setBandwidth("10");
        data.setBandwidth("11");
        data.setJitter("10");
        data.setLost_total_datagrams("10");
        data.setTotal_datagrams("10");
        data.setRetr("10");
        data.setCwnd("10");
        DataD dataD = new DataD(data.getCaseName(), JSON.toJSONString(data));
        dataD.setDate(TimeUtil.now());
        testResultMapper.insertTestResult(dataD);
    }
    @Test
    public void testDeleteAllByCaseName() {
        testResultMapper.deleteAllByCaseName("test");
    }

    @Test
    public void testGetAll() {
        List<DataD> test = testResultMapper.getAll();
        DataD dataD = test.get(0);
        System.out.println(dataD.toString());
        //TestResult data = JSONObject.parseObject(dataD.getData(), TestResult.class);
        TestResult1 data = JSONObject.parseObject(dataD.getData(),TestResult1.class);
        System.out.println(data);
    }
}