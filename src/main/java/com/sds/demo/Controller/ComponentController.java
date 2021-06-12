package com.sds.demo.Controller;


import com.sds.demo.Entity.Component;
import com.sds.demo.Service.ComponentService;
import com.sds.demo.VO.*;
import com.sds.demo.form.ComponentForm;
import com.sds.demo.util.TimeUtil;
import org.springframework.web.bind.annotation.*;



/**
 * @Author Voidmian
 * @Date 2021/1/27 16:18
 */
@CrossOrigin("*")
@RestController
@RequestMapping("/component")
public class ComponentController {
    ComponentService componentService;
    public ComponentController(ComponentService componentService) {
        this.componentService = componentService;
    }


    @GetMapping("/get_all")
    public BaseVO<BaseListVO> getAllComponent(
            @RequestParam(value = "page_size",defaultValue = "10",required = false) Integer pageSize,
            @RequestParam(value = "page_index",defaultValue = "0",required = false)Integer pageIndex) {
        BaseListVO<ComponentVO> baseListVO = componentService.getAllComponentPage(pageSize, pageIndex);
        return new BaseVO<BaseListVO>("success",baseListVO,200);
    }


    @PostMapping("/insert")
    public BaseVO<Integer> insertComponent(@RequestBody ComponentForm componentForm) {
        ComponentVO componentVO = new ComponentVO();
        String sLocation = componentForm.getSLocation();
        String suffix = sLocation.substring(sLocation.lastIndexOf("."));
        componentVO.setName(componentForm.getName() + suffix);
        componentVO.setDesc(componentForm.getDesc());
        componentVO.setCommand(componentForm.getCommand());
        componentVO.setCreateTime(TimeUtil.now());
        componentVO.setUpdateTime(TimeUtil.now());
        String location = componentService.deployComponent(componentVO,sLocation);
        return new BaseVO<>("success", 1, 200);
    }
}
