package com.sds.demo.Entity;

import javax.swing.text.IconView;
import javax.xml.crypto.Data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sds.demo.VO.ComponentVO;
import lombok.*;
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
    private int id;
    private String name;
    private String desc;
    private String command;
    private String location;
    @JsonProperty("create_time")
    private LocalDateTime createTime;
    @JsonProperty("update_time")
    private LocalDateTime updateTime;

    public ComponentVO transForVO(){
        ComponentVO componentVO = new ComponentVO();
        componentVO.setId(this.id);
        componentVO.setCommand(this.command);
        componentVO.setDesc(this.desc);
        componentVO.setName(this.name);
        componentVO.setCreateTime(this.createTime);
        componentVO.setUpdateTime(this.updateTime);
        return componentVO;
    }
}
