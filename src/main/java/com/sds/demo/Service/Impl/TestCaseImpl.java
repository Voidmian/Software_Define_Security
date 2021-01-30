package com.sds.demo.Service.Impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.sds.demo.Entity.BaseList;

import com.sds.demo.Entity.TestCase;
import com.sds.demo.Service.TestCaseService;
import com.sds.demo.VO.BaseListVO;

import com.sds.demo.VO.TestCaseVO;

import com.sds.demo.VO.TestResultDetailVO;
import com.sds.demo.converter.TestCaseConverter;
import com.sds.demo.dao.TestCaseMapper;
import com.sds.demo.util.ResultHandle;
import com.sds.demo.util.SshCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

/**
 * @Author Voidmian
 * @Date 2021/1/28 16:49
 */
@Service
public class TestCaseImpl implements TestCaseService {
    private final TestCaseMapper testCaseMapper;

    public TestCaseImpl(TestCaseMapper testCaseMapper) {
        this.testCaseMapper = testCaseMapper;
    }

    public BaseListVO<TestCaseVO> getAllComponentPage(Integer pageSize, Integer pageIndex) {
        BaseList<TestCase> baseList = new BaseList<>(pageSize, pageIndex);
        baseList.setList(testCaseMapper.getAllPage(baseList.getPageSize(), baseList.getOffset()));
        return TestCaseConverter.convertListDV(baseList);
    }

    public String insert(TestCaseVO testCaseVO) {
        TestCase testCase = TestCaseConverter.convertVD(testCaseVO);
        testCaseMapper.insertTestCase(testCase);
        return "ok";
    }

    public String update(TestCaseVO testCaseVO) {
        TestCase testCase = TestCaseConverter.convertVD(testCaseVO);
        testCaseMapper.updateTestCase(testCase);
        return "ok";
    }

    public String delete(int id) {
        testCaseMapper.deleteTestCase(id);
        return "ok";
    }

    public TestResultDetailVO startCase(int id) {
        TestCase testCase = testCaseMapper.getOneById(id);
        SshCommand sshCommand = SshCommand.getInstance();
        sshCommand.startTestCase(testCase);//todo: sleep 等待
        String localPath = "本地路径+本地文件名"; //todo: 林建东配置
        String remotePath = "远端路径"; //todo: 林建东配置
        String remoteFileName = "远端文件名";//todo: 林建东配置
        sshCommand.download(localPath, remotePath, remoteFileName);
        ResultHandle resultHandle = new ResultHandle();
        resultHandle.handle(localPath);
        return new TestResultDetailVO(testCase.getId(), resultHandle.getTransRate(), resultHandle.getTransScale());
    }

}
