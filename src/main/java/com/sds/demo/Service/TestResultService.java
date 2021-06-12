package com.sds.demo.Service;

import com.sds.demo.Entity.TestResult;
import com.sds.demo.Entity.DataD;
import com.sds.demo.VO.BaseListVO;
import com.sds.demo.VO.TestCaseVO;
import com.sds.demo.VO.TestResultDetailVO;
import com.sds.demo.VO.TestResultVO;

import java.util.List;

/**
 * @Author Voidmian
 * @Date 2021/1/28 17:50
 */
public interface TestResultService {
    public List<DataD> getListByCaseName(String name);
    public String insert(DataD data);
    Integer deleteAllByCaseName(String name);
    List<DataD> getAll();
}
