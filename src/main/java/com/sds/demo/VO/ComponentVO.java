package com.sds.demo.VO;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author Voidmian
 * @Date 2021/1/27 19:16
 */
@Data
public class ComponentVO {
    private Integer id;
    private String name;
    private String desc;
    private String command;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
