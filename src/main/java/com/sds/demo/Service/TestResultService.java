package com.sds.demo.Service;

import com.sds.demo.Entity.Data;
import com.sds.demo.VO.BaseListVO;
import com.sds.demo.VO.TestCaseVO;
import com.sds.demo.VO.TestResultDetailVO;
import com.sds.demo.VO.TestResultVO;

/**
 * @Author Voidmian
 * @Date 2021/1/28 17:50
 */
public interface TestResultService {
    public BaseListVO<TestResultVO> getAllTestResultPageByCaseId(Integer caseId,Integer pageSize, Integer pageIndex);
    public TestResultDetailVO getTestResultById(Integer id);
    public String insert(Data data);
    public String delete(int id);
    public Integer deleteAllById(int caseId);

}
