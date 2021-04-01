package com.sds.demo.Service.Impl;

import com.sds.demo.Entity.BaseList;
import com.sds.demo.Entity.Component;
import com.sds.demo.Service.ComponentService;
import com.sds.demo.VO.BaseListVO;
import com.sds.demo.VO.ComponentVO;
import com.sds.demo.converter.ComponentConverter;
import com.sds.demo.dao.ComponentMapper;
import com.sds.demo.util.SshCommand;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Voidmian
 * @Date 2021/1/25 15:40
 */
@Service
public class ComponentImpl implements ComponentService {
    private final ComponentMapper componentMapper;
    @Value("${Component.B.ip}")
    String host;                     //todo：需要配置B
    @Value("${Component.B.port")
    private int port;
    @Value("${Component.B.username}")
    String username;
    @Value("${Component.B.password}")//todo：需要配置B
    String password;                 //todo：需要配置B
    @Value("${Component.B.remote}")
    String remote;                  //todo：需要配置B 镜像存放地址


    public ComponentImpl(ComponentMapper componentMapper) {
        this.componentMapper = componentMapper;
    }

    @Override
    public Component getComponentById(int id) {
        return componentMapper.getOneById(id);
    }

    public List<Component> getAllComponent() {
        return componentMapper.getAll();
    }

    @Override
    public Integer insert(ComponentVO componentVO, String location) {
        Component component = ComponentConverter.convertVD(componentVO);
        component.setLocation(location);
        return componentMapper.insertComponent(component);
    }

    @Override
    public String deployComponent(ComponentVO componentVO, String sLocation) {
        int port = 22;
        SshCommand sshCommand = new SshCommand(host, port, username, password);
        try {
            String out = sshCommand.copy(sLocation, remote, componentVO.getName());
            Component component = ComponentConverter.convertVD(componentVO);
            component.setLocation(remote + "/" + componentVO.getName());
            componentMapper.insertComponent(component);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        return "ok";
    }
    @Override
    public void operateComponent(Component component, String operate) {

    }

    public BaseListVO<ComponentVO> getAllComponentPage(Integer pageSize, Integer pageIndex) {
        BaseList<Component> baseList = new BaseList<>(pageSize, pageIndex);
        baseList.setList(componentMapper.getAllPage(baseList.getPageSize(), baseList.getOffset()));
        return ComponentConverter.convertListDV(baseList);
    }

    /**
     * ssh后在远端执行命令
     */
    public void sshComponent(Component component) {
        String local = "D:\\tes1t.txt";
        String[] commands = new String[1];
        commands[0] = component.getCommand();

        SshCommand sshCommand = new SshCommand(host, port, username, password);

        try {
            String out = sshCommand.exeCommands(commands);
            System.out.println(out);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
