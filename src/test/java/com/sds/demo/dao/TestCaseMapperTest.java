package com.sds.demo.dao;

import junit.framework.TestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @ClassName: TestCaseMapperTest
 * @Description: TODO
 * @author: XQC
 * @date: 2021/4/5  19:54
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCaseMapperTest extends TestCase {
    @Autowired
    TestCaseMapper testCaseMapper;
    @Test
    public void testGetOneByName() {
        System.out.println(testCaseMapper.getOneByName("213"));
    }
}