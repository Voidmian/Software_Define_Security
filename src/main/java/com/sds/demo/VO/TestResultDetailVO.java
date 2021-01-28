package com.sds.demo.VO;

import com.alibaba.fastjson.JSONArray;
import com.sds.demo.util.TimeUtil;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

/**
 * @Author Voidmian
 * @Date 2021/1/28 20:02
 */
@Data
@NoArgsConstructor
public class TestResultDetailVO {
    Integer id;
    Integer caseId;
    String transRate;
    String transScale;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;


    public TestResultDetailVO(int caseId, int[][] transRate, int[][] transScale) {
        this.caseId = caseId;
        this.createTime = TimeUtil.now();
        this.updateTime = TimeUtil.now();
        this.transRate = JSONArray.toJSONString(transRate);
        this.transScale = JSONArray.toJSONString(transScale);
    }
}
