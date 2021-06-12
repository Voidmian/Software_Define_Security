package com.sds.demo.converter;

import com.alibaba.fastjson.JSON;
import com.sds.demo.Entity.DataD;
import com.sds.demo.Entity.TestResult;
import com.sds.demo.Entity.TestResult1;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: JSON2Data
 * @Description: TODO
 * @author: XQC
 * @date: 2021/4/3  19:12
 */
public class JSON2Data {
    public List<TestResult1> convert(List<DataD> list) {
        List<TestResult1> res = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            TestResult1 result1 = JSON.parseObject(list.get(i).getData(), TestResult1.class);
            result1.setDate(list.get(i).getDate());
            res.add(result1);
        }
        return res;
    }
}
