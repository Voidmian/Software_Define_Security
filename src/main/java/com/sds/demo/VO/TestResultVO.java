package com.sds.demo.VO;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sds.demo.util.TimeUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;

/**
 * @Author Voidmian
 * @Date 2021/1/28 13:53
 */
@Data
@NoArgsConstructor
public class TestResultVO {
    Integer id;
    Integer caseId;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;

    public TestResultVO(int caseId) {
        this.caseId = caseId;
        this.createTime = TimeUtil.now();
        this.updateTime = TimeUtil.now();
    }

}
