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
    private String A_ip;//客户端A的ip
    @Value("${Iperf.C.ip}")
    private String C_ip;//服务器C的ip
    @Value("${Iperf.testPort}")
    private String port;//服务器端监听的端口
    @Value("${Iperf.remote}")
    public String remoteAddr;

    //如果ip和port写死，则启用该构造函数
    /*public StartIperf(){
        this.ip = "";
        this.port = "";
    }*/


    //服务器D向客户端A发送的启动命令
    public String startACommand(String iD, IperfParams iperfParams){
        //构造出要执行的命令
        String cmd =  "iperf3 -c "+"192.168.12.1"+" -p "+ "8080";
        if(iperfParams.getProtocol().equals("udp")  )
            cmd = cmd + " -u";
        if(iperfParams.getBidirectionalTest().equals("yes"))
            cmd = cmd + " -R";
        if(iperfParams.getBufferLength().length()>0)
            cmd = cmd + " -l " + iperfParams.getBufferLength();
        if(iperfParams.getIpv4_6().equals("4"))
            cmd = cmd + " -4";
        if(iperfParams.getIpv4_6().equals("6"))
            cmd = cmd + " -6";
        if(iperfParams.getMss().length() > 0)
            cmd = cmd + " -M " + iperfParams.getMss();
        if(iperfParams.getTCPWindow().length() > 0)
            cmd = cmd + " -w " + iperfParams.getTCPWindow();
        if(iperfParams.getTimeSlot() != 0)
            cmd = cmd + " -i " + iperfParams.getTimeSlot();
        if(iperfParams.getBandwidthLimit().length() > 0)
            cmd = cmd + " -b " + iperfParams.getBandwidthLimit();
        if(iperfParams.getTotalPackages().length() > 0) {
            cmd = cmd + " -n " + iperfParams.getTotalPackages();
        }
        if(iperfParams.getTotalTime() != 0) {
            cmd = cmd + " -t " + iperfParams.getTotalTime();
        }
        cmd = cmd + " -V >/home/sender/iperf3_file/" + iD + ".txt\n";
        return cmd;
    }

    //服务器D向客户端C发送的启动命令
    public String startCCommand(String iD){
        String cmd = "iperf3 -s -p " + "8080" + " -V >" + "/iperf3_file/" + iD + ".txt -1\n";
        return cmd;
    }

    public String get_remoteAdddress(){
        System.out.println("/home/sender/");
        return "/home/sender/";
    }
}