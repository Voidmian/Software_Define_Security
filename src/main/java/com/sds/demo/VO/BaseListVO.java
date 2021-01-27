package com.sds.demo.VO;

import com.sds.demo.Entity.BaseList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Author Voidmian
 * @Date 2021/1/27 16:28
 */
@Data
public class BaseListVO<T> {
    private Integer pageSize;
    private Integer pageIndex;
    private List<T> list;
    public BaseListVO(Integer pageSize, Integer pageIndex) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }
}