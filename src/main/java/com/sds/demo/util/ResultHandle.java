package com.sds.demo.util;

import lombok.Data;

/**
 * @Author Voidmian
 * @Date 2021/1/28 21:54
 */
@Data
public class ResultHandle {
    private int[][] transRate;
    private int[][] transScale;
    public void handle(String path) {
        int[][] a = {{1, 2}, {3, 4}, {4, 5}};
        int[][] b = {{1, 2}, {3, 4}, {4, 5}};
        transRate = a;
        transScale = b;
    }
}
