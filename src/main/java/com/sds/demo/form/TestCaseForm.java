package com.sds.demo.form;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * @Author Voidmian
 * @Date 2021/1/28 17:19
 */
@Data
public class TestCaseForm {
    @JsonProperty("id")
    int id;

    @JsonProperty("name")
    String name;

    @JsonProperty("desc")
    String desc;

    @JsonProperty("protocol")
    String protocol;

    @JsonProperty("concurrency")
    int concurrency;

    @JsonProperty("seconds")
    int seconds;
}
