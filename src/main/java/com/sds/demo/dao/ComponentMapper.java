package com.sds.demo.dao;

import com.sds.demo.Entity.Component;
import org.apache.ibatis.annotations.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author Voidmian
 * @Date 2021/1/25 11:42
 */

@org.springframework.stereotype.Component
public interface ComponentMapper {
    Component getOneById(int id);
    List<Component> getListByName(String name);
    List<Component> getAllPage(int pageSize, int offset);
    List<Component> getAll();
    int insertComponent(Component component);
    int updateComponent(Component component);
    int deleteComponent(int id );
}
