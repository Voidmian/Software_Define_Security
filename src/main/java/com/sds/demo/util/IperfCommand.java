package com.sds.demo.util;


import com.sds.demo.Entity.IperfParams;

/**
 * Created by linjiandong on 2021/3/23.
 * Code on Server side D
 * To send order to Iperf Client side A and Iperf Server side C
 */

public class IperfCommand {

    private static String A_ip = "";//客户端A的ip
    private static String C_ip = "";//服务器C的ip
    private static String port = "";//服务器端监听的端口

    //如果ip和port写死，则启用该构造函数
    /*public StartIperf(){
        this.ip = "";
        this.port = "";
    }*/

    public IperfCommand(String A_ip, String C_ip, String port){
        this.A_ip = A_ip;
        this.C_ip = C_ip;
        this.port = port;
    }

    //服务器D向客户端A发送的启动命令
    public String startACommand(String iD, IperfParams component) throws Exception{
        //构造出要执行的命令
        String cmd =  "iperf3 -c "+C_ip+" -p "+port;
        if(component.getProtocol() == "udp")
            cmd = cmd + " -u";
        if(component.getBidirectionalTest() == "yes")
            cmd = cmd + " -R";
        if(component.getBufferLength() != "")
            cmd = cmd + " -l " + component.getBufferLength();
        if(component.getIpv4_6() == "4")
            cmd = cmd + " -4";
        if(component.getIpv4_6() == "6")
            cmd = cmd + " -6";
        if(component.getMss() != "")
            cmd = cmd + " -M " + component.getMss();
        if(component.getTCPWindow() != "")
            cmd = cmd + " -w " + component.getTCPWindow();
        if(component.getTimeSlot() != "")
            cmd = cmd + " -i " + component.getTimeSlot();
        if(component.getTotalPackages() != "")
            cmd = cmd + " -n " + component.getTotalPackages();
        if(component.getTotalTime() != "")
            cmd = cmd + " -t " + component.getTotalTime();
        if(component.getBandwidthLimit() != "")
            cmd = cmd + " -b " + component.getBandwidthLimit();
        cmd = cmd + " -V >" + iD +".txt ";
        return cmd;
    }

    //服务器D向客户端C发送的启动命令
    public String startCCommand(String iD){
        String cmd = "iperf3 -s -p " + this.port + " -V >" + iD + ".txt";
        return cmd;
    }

    public String get_remoteAdddress(){
        System.out.println("/home/sender/");
        return "/home/sender/";
    }




}