package com.sds.demo.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author Voidmian
 * @Date 2021/1/27 21:14
 */
@Data
public class ComponentForm {
    @JsonProperty("name")
    String name;

    @JsonProperty("command")
    String command;

    @JsonProperty("desc")
    String desc;

    @JsonProperty("s_location")
    String sLocation;
}
