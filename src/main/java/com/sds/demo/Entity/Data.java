package com.sds.demo.Entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.SplittableRandom;

/**
 * Created by linjiandong on 2021/3/23.
 */
public class Data {
    private String id;
    private String protocol = "";
    private ArrayList intercal= new ArrayList<>();
    private ArrayList transfer= new ArrayList<>();
    private ArrayList bandwidth= new ArrayList<>();
    private ArrayList jitter= new ArrayList<>();
    private ArrayList lost_total_datagrams= new ArrayList<>();
    private ArrayList total_datagrams= new ArrayList<>();
    private ArrayList retr = new ArrayList<>();
    private ArrayList cwnd = new ArrayList<>();
    private String transfer_avg= "";
    private String bandwidth_avg= "";
    private String jitter_avg= "";
    private String lost_total_datagrams_avg= "";
    private String cwnd_avg= "";

    public Data(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProtocol() { return protocol; }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public ArrayList getIntercal() {
        return intercal;
    }

    public void setIntercal(String intercal) {
        this.intercal.add(intercal);
    }

    public ArrayList getTransfer() {
        return transfer;
    }

    public void setTransfer(String transfer) {
        this.transfer.add(transfer);
    }

    public ArrayList getBandwidth() {
        return bandwidth;
    }

    public void setBandwidth(String bandwidth) {
        this.bandwidth.add(bandwidth);
    }

    public ArrayList getJitter() {
        return jitter;
    }

    public void setJitter(String  jitter) {
        this.jitter.add(jitter);
    }

    public ArrayList getLost_total_datagrams() {
        return lost_total_datagrams;
    }

    public void setLost_total_datagrams(String lost_total_datagrams) {
        this.lost_total_datagrams.add(lost_total_datagrams);
    }

    public ArrayList getTotal_datagrams() {
        return total_datagrams;
    }

    public void setTotal_datagrams(String total_datagrams) {
        this.total_datagrams.add(total_datagrams);
    }

    public ArrayList getCwnd() {
        return cwnd;
    }

    public void setCwnd(String cwnd) {
        this.cwnd.add(cwnd);
    }

    public ArrayList getRetr() {
        return cwnd;
    }

    public void setRetr(String retr) {
        this.retr.add(retr);
    }

    public String getTransfer_avg() {
        return transfer_avg;
    }

    public void setTransfer_avg() {

        this.transfer_avg = calculateAvg(this.transfer);
    }

    public String getBandwidth_avg() {
        return bandwidth_avg;
    }

    public void setBandwidth_avg() {

        this.bandwidth_avg = calculateAvg(this.bandwidth);
    }

    public String getJitter_avg() {
        return jitter_avg;
    }

    public void setJitter_avg() {
        double temp_jitter_avg=0;
        double sum=0;
        String temps[];
        for(int i=0;i<jitter.size();i++){
            temps = jitter.get(i).toString().split("ms");
            sum+=Double.parseDouble(temps[0]);
        }
        temp_jitter_avg=sum/(jitter.size());
        BigDecimal jitter_avg = new BigDecimal(temp_jitter_avg);
        this.jitter_avg = String.valueOf(jitter_avg.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue())+"ms";
    }

    public String getLost_total_datagrams_avg() {
        return lost_total_datagrams_avg;
    }

    public void setLost_total_datagrams_avg() {
        double temp_lost_total_datagrams_avg=0;
        double sum=0;
        String temps[];
        for(int i=0;i<lost_total_datagrams.size();i++){
            temps = lost_total_datagrams.get(i).toString().split("[\\(%]");
            sum+=Double.parseDouble(temps[1]);
        }
        temp_lost_total_datagrams_avg=sum/(lost_total_datagrams.size());
        BigDecimal lost_total_datagrams_avg = new BigDecimal(temp_lost_total_datagrams_avg);
        this.lost_total_datagrams_avg = String.valueOf(lost_total_datagrams_avg.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue())+"%";
    }

    public String getCwnd_avg() {
        return lost_total_datagrams_avg;
    }

    public void setCwnd_avg() {
        this.cwnd_avg = calculateAvg(this.cwnd);
    }

    public String calculateAvg(ArrayList<String> input){
        double temp_avg=0;
        double sum = 0;
        String temps[];
        String unitage="";
        for(int i=0;i<input.size();i++){
            temps = input.get(i).toString().split("[01234567890.]+");
            unitage = temps[1];
            temps = input.get(i).toString().split("[KMGTB]");
            if(unitage.substring(0,1).equals("K") && unitage.length()<7)
                sum+=Double.parseDouble(temps[0]) * 1024.0;
            else if(unitage.substring(0,1).equals("M") && unitage.length()<7)
                sum+=Double.parseDouble(temps[0]) * 1024.0 * 1024.0;
            else if(unitage.substring(0,1).equals("G") && unitage.length()<7)
                sum+=Double.parseDouble(temps[0]) * 1024.0 * 1024.0 * 1024.0;
            else if(unitage.substring(0,1).equals("T") && unitage.length()<7)
                sum+=Double.parseDouble(temps[0]) * 1024.0 * 1024.0 * 1024.0 * 1024.0;
            else if(unitage.substring(0,1).equals("B") && unitage.length()<7)
                sum+=Double.parseDouble(temps[0]);
            else if(unitage.substring(0,1).equals("K") && unitage.length()>7)
                sum+=Double.parseDouble(temps[0]) * 1000.0;
            else if(unitage.substring(0,1).equals("M") && unitage.length()>7)
                sum+=Double.parseDouble(temps[0]) * 1000.0 * 1000.0;
            else if(unitage.substring(0,1).equals("G") && unitage.length()>7)
                sum+=Double.parseDouble(temps[0]) * 1000.0 * 1000.0 * 1000.0;
            else if(unitage.substring(0,1).equals("T") && unitage.length()>7)
                sum+=Double.parseDouble(temps[0]) * 1000.0 * 1000.0 * 1000.0 * 1000.0;
            else if(unitage.substring(0,1).equals("b") && unitage.length()>7)
                sum+=Double.parseDouble(temps[0]);
        }
        temp_avg=sum/(input.size());
        if(temp_avg < 1024.0 && unitage.length()<7)
        {
            BigDecimal avg = new BigDecimal(temp_avg);
            return String.valueOf(avg.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue())+ "Bytes";
        }
        else if(temp_avg < 1024.0*1024.0 && temp_avg >= 1024.0 && unitage.length()<7){
            BigDecimal avg = new BigDecimal(temp_avg/1024.0);
            return String.valueOf(avg.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue())+ "KBytes";
        }
        else if(temp_avg < 1024.0*1024.0*1024.0 && temp_avg >= 1024.0*1024.0 && unitage.length()<7){
            BigDecimal avg = new BigDecimal(temp_avg/1024.0/1024.0);
            return String.valueOf(avg.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue())+ "MBytes";
        }
        else if(temp_avg < 1024.0*1024.0*1024.0*1024.0 && temp_avg >= 1024.0*1024.0*1024.0 && unitage.length()<7){
            BigDecimal avg = new BigDecimal(temp_avg/1024.0/1024.0/1024.0);
            return String.valueOf(avg.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue())+ "GBytes";
        }
        else if(temp_avg < 1024.0*1024.0*1024.0*1024.0*1024.0 && temp_avg >= 1024.0*1024.0*1024.0*1024.0 && unitage.length()<7){
            BigDecimal avg = new BigDecimal(temp_avg/1024.0/1024.0/1024.0/1024.0);
            return String.valueOf(avg.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue())+ "TBytes";
        }
        else if(temp_avg < 1000.0 && unitage.length()>7)
        {
            BigDecimal avg = new BigDecimal(temp_avg);
            return String.valueOf(avg.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue())+ "bits/sec";
        }
        else if(temp_avg < 1000.0*1000.0 && temp_avg >= 1000.0 && unitage.length()>7){
            BigDecimal avg = new BigDecimal(temp_avg/1000.0);
            return String.valueOf(avg.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue())+ "Kbits/sec";
        }
        else if(temp_avg < 1000.0*1000.0*1000.0 && temp_avg >= 1000.0*1000.0 && unitage.length()>7){
            BigDecimal avg = new BigDecimal(temp_avg/1000.0/1000.0);
            return String.valueOf(avg.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue())+ "Mbits/sec";
        }
        else if(temp_avg < 1000.0*1000.0*1000.0*1000.0 && temp_avg >= 1000.0*1000.0*1000.0 && unitage.length()>7){
            BigDecimal avg = new BigDecimal(temp_avg/1000.0/1000.0/1000.0);
            return String.valueOf(avg.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue())+ "Gbits/sec";
        }
        else if(temp_avg < 1000.0*1000.0*1000.0*1000.0*1000.0 && temp_avg >= 1000.0*1000.0*1000.0*1000.0 && unitage.length()>7){
            BigDecimal avg = new BigDecimal(temp_avg/1000.0/1000.0/1000.0/1000.0);
            return String.valueOf(avg.setScale(3,BigDecimal.ROUND_HALF_UP).doubleValue())+ "Tbits/sec";
        }
        else {
            return "";
        }
    }

}

