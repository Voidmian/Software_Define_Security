package com.sds.demo.Entity;

import com.sds.demo.Entity.Component;
import lombok.Data;

/**
 * Created by linjiandong on 2021/3/23.
 */
@Data
public class IperfParams {

    private String protocol;
    private int totalTime;
    private int timeSlot;
    private String bandwidthLimit;
    private String totalPackages;
    private String bufferLength;
    private String bidirectionalTest;
    private String TCPWindow;
    private String mss;
    private String ipv4_6;

    public IperfParams(String protocol, int totalTime, int timeSlot, String bandwidthLimit, String totalPackages,
                     String bufferLength, String bidirectionalTest, String TCPWindow, String mss, String ipv4_6){
        this.protocol = protocol;
        this.totalPackages = totalPackages;
        this.totalTime = totalTime;
        this.timeSlot = timeSlot;
        this.bandwidthLimit = bandwidthLimit;
        this.bufferLength = bufferLength;
        this.bidirectionalTest = bidirectionalTest;

        this.TCPWindow = TCPWindow;
        this.mss = mss;
        this.ipv4_6 = ipv4_6;
    }
}
