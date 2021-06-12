package com.sds.demo.util;

/**
 * Created by linjiandong on 2021/3/23.
 * Code on Server side D
 * To send order to Iperf Client side A and Iperf Server side C
 */

import com.sds.demo.Entity.IperfParams;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StartIperf {

    private  String A_ip = "47.98.228.148";
    private  int A_port = 22;
    private  String A_username = "root";
    private  String A_password = "yjj0413_Aly";
    private  String C_ip = "47.98.228.148";
    private  int C_port = 22;
    private  String C_username = "root";
    private  String C_password = "yjj0413_Aly";
    private  String netSec = "";//最终返回的结果


    //如果ip和port写死，则启用该构造函数
    /*public StartIperf(){
        this.ip = "";
        this.port = "";
    }*/


    public StartIperf(String A_ip, String C_ip, int port) {
        this.A_ip = A_ip;
        this.C_ip = C_ip;
        this.A_port = port;
    }

    //服务器D向客户端A发送命令启动
    public  String startAOrder(String iD, IperfParams iperfParams) throws Exception {

        //构造出要执行的命令
        String cmd = "iperf3 -c " + C_ip + " -p " + A_port;
        if (iperfParams.getProtocol() == "udp")
            cmd = cmd + " -u";
        if (iperfParams.getBidirectionalTest() == "yes")
            cmd = cmd + " -R";
        if (iperfParams.getBufferLength() != "")
            cmd = cmd + " -l " + iperfParams.getBufferLength();
        if (iperfParams.getIpv4_6() == "4")
            cmd = cmd + " -4";
        if (iperfParams.getIpv4_6() == "6")
            cmd = cmd + " -6";
        if (iperfParams.getMss() != "")
            cmd = cmd + " -M " + iperfParams.getMss();
        if (iperfParams.getTCPWindow() != "")
            cmd = cmd + " -w " + iperfParams.getTCPWindow();
        if (iperfParams.getTimeSlot() != 0)
            cmd = cmd + " -i " + iperfParams.getTimeSlot();
        if (iperfParams.getTotalPackages() != "")
            cmd = cmd + " -n " + iperfParams.getTotalPackages();
        if (iperfParams.getTotalTime() != 0)
            cmd = cmd + " -t " + iperfParams.getTotalTime();
        if (iperfParams.getBandwidthLimit() != "")
            cmd = cmd + " -b " + iperfParams.getBandwidthLimit();
        cmd = cmd + " -V >" + iD + ".txt ";
        //System.out.println("test_print_cmd:" + cmd);
        return cmd;
    }

    public String startCOrder(String iD) {
        String cmd = "iperf3 -s -p " + this.A_port + " -V >" + iD + ".txt";
        //System.out.println("test_print_cmd" + cmd);
        return cmd;
    }
}
