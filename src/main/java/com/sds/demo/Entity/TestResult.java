package com.sds.demo.Entity;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author Voidmian
 * @Date 2021/1/28 13:08
 */
@lombok.Data
@Repository
public class TestResult implements Serializable {
    private Integer id;
    private Integer caseId;
    private String transRate;
    private String transScale;
    private String data;
    @JsonProperty("create_time")
    private LocalDateTime createTime;
    @JsonProperty("update_time")
    private LocalDateTime updateTime;
}

