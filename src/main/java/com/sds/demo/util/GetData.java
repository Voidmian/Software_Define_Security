package com.sds.demo.util;

import com.sds.demo.Entity.TestResult;
import com.sds.demo.Entity.TestResult;
import org.springframework.stereotype.Component;

import java.io.*;


/**
 * Created by linjiandong on 2021/3/24.
 */
@Component
public class GetData {

    public TestResult getDataFromFile(String localaddr, String filename) throws FileNotFoundException, IOException
    {
        FileReader file = new FileReader(localaddr + "/" + filename);
        try
        {
            BufferedReader br = new BufferedReader(file);
            String id[] = filename.split(".txt");
            TestResult data = new TestResult(id[0]);
            String temp = "";
            String totaltime = "";
            String protocol = "";
            int time = 0;
            int count = 0;
            while (temp != null)
            {
                temp = br.readLine();
                if(temp.contains("- - - -"))
                    break;
                if (temp != null && temp.contains("omitting"))
                {
                    String temps[] = temp.split(",");
                    protocol = temps[0].substring(25, 28);
                    data.setProtocol(protocol);
                    temps = temps[4].split(" ");
                    time = new Integer(temps[1]).intValue();
                }
                if (temp != null && temp.contains(" sec ") && !temp.contains("connected"))
                {
                    String temps[] = temp.split(" +");
                    if (protocol.equals("TCP"))
                    {
                        data.setIntercal(temps[2] + temps[3]);
                        data.setTransfer(temps[4] + temps[5]);
                        data.setBandwidth(temps[6] + temps[7]);
                        data.setTransfer_avg();
                        data.setBandwidth_avg();
                        if (temps.length > 8)
                        {
                            data.setRetr(temps[8]);
                            data.setCwnd(temps[9] + temps[10]);
                            data.setCwnd_avg();
                        }
                    }
                    else if (protocol.equals("UDP")) {
                        data.setIntercal(temps[2]+temps[3]);
                        data.setTransfer(temps[4]+temps[5]);
                        data.setBandwidth(temps[6]+temps[7]);
                        data.setTransfer_avg();
                        data.setBandwidth_avg();
                        if(temps.length>9){
                            data.setJitter(temps[8]+temps[9]);
                            data.setLost_total_datagrams(temps[10]+temps[11]);
                            data.setJitter_avg();
                            data.setLost_total_datagrams_avg();
                        }
                        else{
                            data.setTotal_datagrams(temps[8]);
                        }
                    }
                }
            }
            return data;
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
