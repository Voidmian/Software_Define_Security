package com.sds.demo.Service.Impl;

import com.sds.demo.Entity.DataD;
import com.sds.demo.Service.TestResultService;
import com.sds.demo.dao.TestResultMapper;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Voidmian
 * @Date 2021/1/28 18:00
 */
@Service
public class TestResultImpl implements TestResultService {
    private final TestResultMapper testResultMapper;

    public TestResultImpl(TestResultMapper testResultMapper) {
        this.testResultMapper = testResultMapper;
    }

    @Override
    public List<DataD> getListByCaseName(String name) {
        List<DataD> listByCaseName = testResultMapper.getListByCaseName(name);
        return listByCaseName;
    }

    @Override
    public String insert(DataD data) {
        testResultMapper.insertTestResult(data);
        return "ok";
    }

    @Override
    public Integer deleteAllByCaseName(String name) {
        return testResultMapper.deleteAllByCaseName(name);
    }

    @Override
    public List<DataD> getAll() {
        return testResultMapper.getAll();
    }

}
