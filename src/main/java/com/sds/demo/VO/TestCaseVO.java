package com.sds.demo.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author Voidmian
 * @Date 2021/1/28 16:54
 */
@Data
public class TestCaseVO {
    private String name;
    private String desc;
    private String protocol;
    private String componentName;
    private String totalTime;
    private String timeSlot;
    private String bandwidthLimit;
    private String totalPackages;
    private String bufferLength;
    private String bidirectionalTest;
    private String TCPWindow;
    private String mss;
    private String ipv4_6;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
