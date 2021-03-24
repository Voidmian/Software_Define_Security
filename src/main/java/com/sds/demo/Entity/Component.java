package com.sds.demo.Entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.sds.demo.VO.ComponentVO;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @Author Voidmian
 * @Date 2021/1/25 11:34
 */


@lombok.Data
@Repository
public class Component implements Serializable {
    private Integer id;
    private String name;
    private String desc;
    private String command;
    private String location;
    @JsonProperty("create_time")
    private LocalDateTime createTime;
    @JsonProperty("update_time")
    private LocalDateTime updateTime;

}
