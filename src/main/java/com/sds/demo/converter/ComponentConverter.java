package com.sds.demo.converter;

import com.sds.demo.Entity.BaseList;
import com.sds.demo.Entity.Component;
import com.sds.demo.VO.BaseListVO;
import com.sds.demo.VO.ComponentVO;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Voidmian
 * @Date 2021/1/27 19:38
 */
public class ComponentConverter {
    static public Component convertVD(ComponentVO componentVO){
        Component component = new Component();
        BeanUtils.copyProperties(componentVO, component);
        return component;
    }

    static public ComponentVO convertDV(Component component){
        ComponentVO componentVO = new ComponentVO();
        BeanUtils.copyProperties(component, componentVO);
        return componentVO;
    }

    static public BaseListVO<ComponentVO> convertListDV(BaseList<Component> baseList) {
        BaseListVO<ComponentVO> baseListVO = new BaseListVO<>(baseList.getPageSize(), baseList.getPageIndex());
        baseListVO.setList(new ArrayList<>());
        for (Component c:baseList.getList()
             ) {
            baseListVO.getList().add(convertDV(c));
        }
        return baseListVO;
    }
}
