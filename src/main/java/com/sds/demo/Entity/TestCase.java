package com.sds.demo.Entity;

import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @Author Voidmian
 * @Date 2021/1/28 14:25
 */
@lombok.Data
@Repository
public class TestCase   {
    private Integer id;
    private String name;
    private String desc;
    private String protocol;
    private Integer concurrency;
    private Integer seconds;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
}
