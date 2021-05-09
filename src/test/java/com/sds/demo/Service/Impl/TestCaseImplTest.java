package com.sds.demo.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sds.demo.Entity.DataD;
import com.sds.demo.Entity.TestResult;
import com.sds.demo.Service.TestResultService;
import com.sds.demo.util.GetData;
import com.sds.demo.util.SSHConnection;
import com.sds.demo.util.TimeUtil;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @ClassName: TestCaseImplTest
 * @Description: TODO
 * @author: XQC
 * @date: 2021/4/2  16:53
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCaseImplTest extends TestCase {

    private GetData getData = new GetData();
    @Autowired
    TestResultImpl testResultService;
    @Test
    public void testStartCase() throws IOException {
        String path = "E:\\WeChat Files\\WeChat Files\\wxid_hb1pnbbi5vzj12\\FileStorage\\File\\2021-04";
        String fileName = "test12.txt";
        TestResult data = getData.getDataFromFile(path, fileName);
        DataD d = new DataD(data.getCaseName(), JSON.toJSONString(data, SerializerFeature.WriteMapNullValue));
        d.setDate(TimeUtil.now());
        testResultService.insert(d);
        System.out.println(d);
    }

    @Test
    public void download() throws IOException {
        SSHConnection sshConnection = new SSHConnection("121.248.51.177", 22, "ctrl_machine", "123456");
        sshConnection.download("E:\\a", "/home/ctrl_machine", "robots.txt");
    }
}