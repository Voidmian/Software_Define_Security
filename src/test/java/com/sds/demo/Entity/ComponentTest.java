package com.sds.demo.Entity;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mysql.cj.xdevapi.JsonArray;

import com.sds.demo.VO.TestResultVO;
import com.sds.demo.converter.TestResultConverter;
import com.sds.demo.dao.ComponentMapper;
import org.junit.Test;


import org.junit.runner.RunWith;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.Assert;

import java.time.LocalDateTime;
import java.util.List;


/**
 * @Author Voidmian
 * @Date 2021/1/25 12:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ComponentTest {
    @Autowired
    private ComponentMapper componentMapper;


    @Test
    public void getOneById() {
        if (componentMapper == null) {
            System.out.println("null");
        } else {
            String name = "aaa";
            Component ans = componentMapper.getOneById(1);
            System.out.println(ans.getLocation());
            Assert.notNull(ans);
        }

    }

    public void getListByName() {
        List<Component> ans = componentMapper.getListByName("lalal");
        System.out.println(ans.size());
    }

    @Test
    public void insert() {
        Component component = new Component();
        component.setDesc("lalal");
        component.setCommand("aaa");
        component.setName("lalal");
        component.setLocation("asdf");
        component.setCreateTime(LocalDateTime.now());
        component.setUpdateTime(LocalDateTime.now());
        int res = componentMapper.insertComponent(component);
        System.out.println(res);
    }

    @Test
    public void update() {
        Component component = new Component();
        component.setId(2);
        component.setDesc("aaa");
        component.setCommand("aaa");
        component.setName("啊撒打发");
        component.setLocation("asdf");
        component.setUpdateTime(LocalDateTime.now());
        int res = componentMapper.updateComponent(component);
        System.out.println(res);
    }

    @Test
    public void delete() {
        int res = componentMapper.deleteComponent(1);
        System.out.println(res);
    }

    @Test
    public void getAll() {
        List res = componentMapper.getAll();
        System.out.println(res.size());
    }

    @Test
    public void getList() {
        int pageSize = 2;
        int pageIndex = 0;
        List res = componentMapper.getAllPage(pageSize, 1);
        System.out.println(res.size());
    }

}