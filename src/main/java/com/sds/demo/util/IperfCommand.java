package com.sds.demo.util;


import com.sds.demo.Entity.IperfParams;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * Created by linjiandong on 2021/3/23.
 * Code on Server side D
 * To send order to Iperf Client side A and Iperf Server side C
 */
@Data
public class IperfCommand {
    @Value("${Iperf.A.ip}")
    private static String A_ip;//客户端A的ip
    @Value("${Iperf.C.ip}")
    private static String C_ip;//服务器C的ip
    @Value("${Iperf.testPort}")
    private static String port;//服务器端监听的端口
    @Value("${Iperf.remote}")
    public static String remoteAddr;

    //如果ip和port写死，则启用该构造函数
    /*public StartIperf(){
        this.ip = "";
        this.port = "";
    }*/


    //服务器D向客户端A发送的启动命令
    public static String startACommand(String iD, IperfParams iperfParams){
        //构造出要执行的命令
        String cmd =  "iperf3 -c "+C_ip+" -p "+port;
        if(iperfParams.getProtocol() == "udp")
            cmd = cmd + " -u";
        if(iperfParams.getBidirectionalTest() == "yes")
            cmd = cmd + " -R";
        if(iperfParams.getBufferLength() != "")
            cmd = cmd + " -l " + iperfParams.getBufferLength();
        if(iperfParams.getIpv4_6() == "4")
            cmd = cmd + " -4";
        if(iperfParams.getIpv4_6() == "6")
            cmd = cmd + " -6";
        if(iperfParams.getMss() != "")
            cmd = cmd + " -M " + iperfParams.getMss();
        if(iperfParams.getTCPWindow() != "")
            cmd = cmd + " -w " + iperfParams.getTCPWindow();
        if(iperfParams.getTimeSlot() != "")
            cmd = cmd + " -i " + iperfParams.getTimeSlot();
        if(iperfParams.getTotalPackages() != "")
            cmd = cmd + " -n " + iperfParams.getTotalPackages();
        if(iperfParams.getTotalTime() != "")
            cmd = cmd + " -t " + iperfParams.getTotalTime();
        if(iperfParams.getBandwidthLimit() != "")
            cmd = cmd + " -b " + iperfParams.getBandwidthLimit();
        cmd = cmd + " -V >" + iD +".txt ";
        return cmd;
    }

    //服务器D向客户端C发送的启动命令
    public static String startCCommand(String iD){
        String cmd = "iperf3 -s -p " + port + " -V >" + iD + ".txt";
        return cmd;
    }

    public String get_remoteAdddress(){
        System.out.println("/home/sender/");
        return "/home/sender/";
    }




}