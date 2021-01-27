package com.sds.demo.Controller;

import com.sds.demo.Entity.BaseList;
import com.sds.demo.Entity.Component;
import com.sds.demo.Service.ComponentService;
import com.sds.demo.VO.*;
import com.sds.demo.converter.ComponentConverter;
import com.sds.demo.form.ComponentForm;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;


/**
 * @Author Voidmian
 * @Date 2021/1/27 16:18
 */

@RestController
@RequestMapping("/component")
public class ComponentController {
    ComponentService componentService;
    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }

//    /**
//     * @param pageSize int
//     * @param pageIndex int
//     */
    @GetMapping("/get_all")
    public BaseVO<BaseListVO> getAllComponent(
            @RequestParam(value = "page_size",defaultValue = "10",required = false) Integer pageSize,
            @RequestParam(value = "page_index",defaultValue = "0",required = false)Integer pageIndex) {
        BaseListVO<ComponentVO> baseListVO = componentService.getAllComponentPage(pageSize, pageIndex);
        return new BaseVO<BaseListVO>("success",baseListVO,200);
    }


    @PostMapping("/insert")
    public BaseVO<Integer> getAllComponent(@RequestBody ComponentForm componentForm) {
        ComponentVO componentVO = new ComponentVO();
        componentVO.setName(componentForm.getName());
        componentVO.setDesc(componentForm.getDesc());
        componentVO.setCommand(componentForm.getCommand());
        componentVO.setCreateTime(LocalDateTime.now());
        componentVO.setUpdateTime(LocalDateTime.now());
        String location = componentService.deployComponent(componentVO,componentForm.getSLocation());
        Integer id = componentService.insert(componentVO,location);
        return new BaseVO<>("success", id, 200);
    }

    @GetMapping("/operate")
    /**
     * @Param id int
     * @Param operation String
     */
    public BaseVO<Integer> startComponent(Integer id,String operation) {
        Component component = componentService.getComponentById(id);
        componentService.operateComponent(component,operation);
        return new BaseVO<>("success", id, 200);
    }

}
