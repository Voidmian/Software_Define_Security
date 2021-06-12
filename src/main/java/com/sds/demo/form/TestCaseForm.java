package com.sds.demo.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author Voidmian
 * @Date 2021/1/28 17:19
 */
@Data
public class TestCaseForm {
    @JsonProperty("name")
    private String name;

    @JsonProperty("desc")
    private String desc;

    @JsonProperty("protocol")
    private String protocol;

    @JsonProperty("componentName")
    private String componentName;

    @JsonProperty("timeSlot")
    private int timeSlot;

    @JsonProperty("totalTime")
    private int totalTime;

    @JsonProperty("bandwidthLimit")
    private String bandwidthLimit;

    @JsonProperty("totalPackages")
    private String totalPackages;

    @JsonProperty("bufferLength")
    private String bufferLength;

    @JsonProperty("bidirectionalTest")
    private String bidirectionalTest;

    @JsonProperty("TCPWindow")
    private String TCPWindow;

    @JsonProperty("mss")
    private String mss;

    @JsonProperty("ipv4_6")
    private String ipv4_6;
}
