package com.sds.demo.Entity;

import lombok.Data;

import java.util.List;

/**
 * @Author Voidmian
 * @Date 2021/1/27 19:19
 */
@Data
public class BaseList<T> {
    private Integer pageSize;
    private Integer pageIndex;
    private Integer offset;
    private List<T> list;
    public BaseList(Integer pageSize, Integer pageIndex){
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.offset = pageSize*pageIndex;
    }
}
