package com.sds.demo.Service.Impl;

import com.sds.demo.Entity.BaseList;
import com.sds.demo.Entity.Component;
import com.sds.demo.Service.ComponentService;
import com.sds.demo.VO.BaseListVO;
import com.sds.demo.VO.ComponentVO;
import com.sds.demo.converter.ComponentConverter;
import com.sds.demo.dao.ComponentMapper;
import com.sds.demo.util.IperfParams;
import com.sds.demo.util.SshCommand;
import com.sds.demo.util.StartIperf;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Voidmian
 * @Date 2021/1/25 15:40
 */
@Service
public class ComponentImpl implements ComponentService {
    private final ComponentMapper componentMapper;

    public ComponentImpl(ComponentMapper componentMapper) {
        this.componentMapper = componentMapper;
    }

    public Component getComponentById(int id) {
        return componentMapper.getOneById(id);
    }

    public List<Component> getAllComponent() {
        return componentMapper.getAll();
    }

    public Integer insert(ComponentVO componentVO, String location) {
        Component component = ComponentConverter.convertVD(componentVO);
        component.setLocation(location);
        return componentMapper.insertComponent(component);
    }

    public String deployComponent(ComponentVO componentVO, String sLocation) {
        int port = 22;

        String host = "47.98.228.148";  //todo：需要配置
        String username = "root";     //todo：需要配置
        String password = "yjj0413_Aly"; //todo：需要配置
        String remote = "/home";    //todo：需要配置
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
        String host = "47.98.228.148";
        int port = 22;
        String username = "root";
        String password = "yjj0413_Aly";
        String local = "D:\\tes1t.txt";
        String remote = "/home";
        String[] commands = new String[2];
        commands[0] = "cd /home\n";
        commands[1] = "ifconfig\n";

        SshCommand sshCommand = new SshCommand(host, port, username, password);
        try {
            String out = sshCommand.exeCommands(commands);
            System.out.println(out);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    public void startIperf(Component component) {
        IperfParams iperfParams = IperfParams.componentToIperfParams(component);
        StartIperf startIperf = new StartIperf();
        try {
            String commandC = startIperf.startCOrder(component.getName() + component.getId());
            SshCommand connectC = new SshCommand(startIperf.getC_ip(), startIperf.getC_port(), startIperf.getC_username(), startIperf.getC_password());
            String out = connectC.exeCommand(commandC);
            String commandA = startIperf.startAOrder(component.getName() + component.getId(), iperfParams);
            SshCommand connectA = new SshCommand(startIperf.getA_ip(), startIperf.getA_port(), startIperf.getA_username(), startIperf.getA_password());
            out = connectA.exeCommand(commandA);
            System.out.println(out);
            Thread.sleep(Integer.parseInt(iperfParams.getTotalTime()) * 1000000);
            String remoteAddr = new String();
            String localAddr = new String();
            connectA.download(localAddr,remoteAddr,component.getName() + component.getId()+".txt");
            connectC.download(localAddr,remoteAddr,component.getName() + component.getId()+".txt");

        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }
}
