package com.sds.demo.Entity;

import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author Voidmian
 * @Date 2021/1/28 14:25
 */
@lombok.Data
@Repository
public class TestCase   {
    private Integer id;
    private String name;
    private String desc;
    private String protocol;
    private Integer timeSlot;
    private Integer totalTime;
    private String componentName;
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
