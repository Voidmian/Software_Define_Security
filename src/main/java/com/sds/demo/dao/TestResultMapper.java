package com.sds.demo.dao;

import com.sds.demo.Entity.DataD;


import java.util.List;

/**
 * @Author Voidmian
 * @Date 2021/1/28 16:08
 */
@org.springframework.stereotype.Component
public interface TestResultMapper {
    List<DataD> getListByCaseName(String name);
    int insertTestResult(DataD data);
    int deleteAllByCaseName(String name);
    List<DataD> getAll();
}
