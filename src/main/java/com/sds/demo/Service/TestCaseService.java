package com.sds.demo.Service;

import com.sds.demo.Entity.TestCase;
import com.sds.demo.VO.BaseListVO;
import com.sds.demo.VO.TestCaseVO;

import java.util.List;

/**
 * @Author Voidmian
 * @Date 2021/1/28 16:49
 */
public interface TestCaseService {
    public BaseListVO<TestCaseVO> getAllComponentPage(Integer pageSize, Integer pageIndex);
    public String insert(TestCaseVO testCaseVO);
    public String delete(int id);
    public void startCase(String name);
    List<TestCase> getAll();
}
