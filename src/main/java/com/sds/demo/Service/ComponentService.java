package com.sds.demo.Service;
import com.sds.demo.Entity.BaseList;
import com.sds.demo.Entity.Component;
import com.sds.demo.VO.BaseListVO;
import com.sds.demo.VO.ComponentVO;

import java.util.List;

/**
 * @Author Voidmian
 * @Date 2021/1/25 15:35
 */
public interface ComponentService {
    public Integer insert(ComponentVO componentVO,String location);
    public String deployComponent(ComponentVO componentVO,String sLocation);
    public Component getComponentByName(String name);
    public BaseListVO<ComponentVO> getAllComponentPage(Integer pageSize, Integer pageIndex);
}
