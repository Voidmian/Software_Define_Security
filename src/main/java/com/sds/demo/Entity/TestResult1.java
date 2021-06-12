package com.sds.demo.Entity;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @Author Voidmian
 * @Date 2021/1/28 13:08
 */
@lombok.Data
@Repository
public class TestResult1 implements Serializable {
    private String caseName;
    private String protocol = "";
    private ArrayList intercal= new ArrayList<>();
    private ArrayList transfer= new ArrayList<>();
    private ArrayList bandwidth= new ArrayList<>();
    private ArrayList jitter= new ArrayList<>();
    private ArrayList lost_total_datagrams= new ArrayList<>();
    private ArrayList total_datagrams= new ArrayList<>();
    private ArrayList retr = new ArrayList<>();
    private ArrayList cwnd = new ArrayList<>();
    private String transfer_avg= "";
    private String bandwidth_avg= "";
    private String jitter_avg= "";
    private String lost_total_datagrams_avg= "";
    private String cwnd_avg= "";
    private LocalDateTime date;
}

