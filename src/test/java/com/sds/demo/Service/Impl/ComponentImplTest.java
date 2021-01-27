package com.sds.demo.Service.Impl;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;



/**
 * @Author Voidmian
 * @Date 2021/1/26 15:55
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ComponentImplTest {
    @Autowired
    private ComponentImpl componentImpl;

    @Test
    public void startComponent() {
        componentImpl.sshComponent(componentImpl.getComponentById(1));
    }
}