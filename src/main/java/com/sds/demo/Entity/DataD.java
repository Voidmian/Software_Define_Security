package com.sds.demo.Entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * @ClassName: DataD
 * @Description: TODO
 * @author: XQC
 * @date: 2021/4/2  12:40
 */
@Data
public class DataD {
    private int id;
    private String caseName;
    private String data;
    private LocalDateTime date;
    public DataD(String caseName, String data) {
        this.caseName = caseName;
        this.data = data;
    }
}
