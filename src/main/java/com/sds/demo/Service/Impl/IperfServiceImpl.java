package com.sds.demo.Service.Impl;

import com.sds.demo.Entity.IperfParams;
import com.sds.demo.Service.IperfService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @ClassName: IperfServiceImpl
 * @Description: TODO
 * @author: XQC
 * @date: 2021/4/1  8:34
 */
@Service
public class IperfServiceImpl implements IperfService {
    @Value("${Iperf.A.ip}")
    private static String A_ip;//客户端A的ip
    @Value("${Iperf.C.ip}")
    private static String C_ip;//服务器C的ip
    @Value("${Iperf.port}")
    private static String port;//服务器端监听的端口

    public String startACommand(String iD, IperfParams iperfParams) throws Exception{
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
        if(iperfParams.getTimeSlot() != 0)
            cmd = cmd + " -i " + iperfParams.getTimeSlot();
        if(iperfParams.getTotalPackages() != "")
            cmd = cmd + " -n " + iperfParams.getTotalPackages();
        if(iperfParams.getTotalTime() != 0)
            cmd = cmd + " -t " + iperfParams.getTotalTime();
        if(iperfParams.getBandwidthLimit() != "")
            cmd = cmd + " -b " + iperfParams.getBandwidthLimit();
        cmd = cmd + " -V >" + iD +".txt ";
        return cmd;
    }

    @Override
    public void startIperf(IperfParams iperfParams) {

    }

    public String startCCommand(String iD){
        String cmd = "iperf3 -s -p " + this.port + " -V >" + iD + ".txt";
        return cmd;
    }


}
