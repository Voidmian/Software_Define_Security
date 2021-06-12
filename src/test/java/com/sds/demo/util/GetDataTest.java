package com.sds.demo.util;

import com.sds.demo.Entity.TestResult;
import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName: GetDataTest
 * @Description: TODO
 * @author: XQC
 * @date: 2021/4/6  11:10
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class GetDataTest extends TestCase {
    @Autowired
    GetData getData;
    @Test
    public void testGetDataFromFile() throws Exception {
        TestResult testResult = getData.getDataFromFile("E:\\", "test2A.txt");
        System.out.println(testResult.toString());

    }
}