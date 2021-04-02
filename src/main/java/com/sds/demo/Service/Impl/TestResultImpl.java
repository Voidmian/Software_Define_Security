package com.sds.demo.Service.Impl;

import com.sds.demo.Entity.BaseList;
import com.sds.demo.Entity.Component;
import com.sds.demo.Entity.Data;
import com.sds.demo.Entity.TestResult;
import com.sds.demo.Service.TestResultService;
import com.sds.demo.VO.BaseListVO;
import com.sds.demo.VO.TestResultDetailVO;
import com.sds.demo.VO.TestResultVO;
import com.sds.demo.converter.ComponentConverter;
import com.sds.demo.converter.TestResultConverter;
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

    public BaseListVO<TestResultVO> getAllTestResultPageByCaseId(Integer caseId, Integer pageSize, Integer pageIndex) {
        BaseList<TestResult> baseList = new BaseList<>(pageSize, pageIndex);
        baseList.setList(testResultMapper.getListByCaseIdPage(caseId, baseList.getPageSize(), baseList.getOffset()));
        return TestResultConverter.convertListDV(baseList);
    }

    public TestResultDetailVO getTestResultById(Integer id) {
        TestResult testResult = testResultMapper.getOneById(id);
        return TestResultConverter.convertDDetailV(testResult);
    }

    public String insert(Data data) {
        testResultMapper.insertTestResult(data);
        return "ok";
    }

/*    public String insert(TestResultDetailVO testResultDetailVO) {
        TestResult testResult = TestResultConverter.convertDetailVD(testResultDetailVO);
        testResultMapper.insertTestResult(testResult);
        return "ok";
    }*/

    public String delete(int id) {
        testResultMapper.deleteTestResult(id);
        return "ok";
    }

    public Integer deleteAllById(int caseId) {
        List<TestResult> res = testResultMapper.getListByCaseId(caseId);
        for (TestResult testResult : res) {
            testResultMapper.deleteTestResult(testResult.getId());
        }
        return res.size();
    }
}
