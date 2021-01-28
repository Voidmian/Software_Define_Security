package com.sds.demo.VO;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @Author Voidmian
 * @Date 2021/1/28 16:54
 */
@Data
public class TestCaseVO {
    private Integer id;
    private String name;
    private String desc;
    private String protocol;
    private Integer concurrency;
    private Integer seconds;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
