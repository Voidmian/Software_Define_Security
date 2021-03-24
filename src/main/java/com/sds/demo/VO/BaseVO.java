package com.sds.demo.VO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Voidmian
 * @Date 2021/1/27 16:22
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BaseVO <T> {
    private String message;
    private T data;
    private Integer code;
}
