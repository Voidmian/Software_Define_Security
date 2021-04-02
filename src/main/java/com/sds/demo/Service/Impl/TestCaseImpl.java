package com.sds.demo.Service.Impl;

import com.sds.demo.Entity.BaseList;

import com.sds.demo.Entity.Component;
import com.sds.demo.Entity.Data;
import com.sds.demo.Entity.IperfParams;
import com.sds.demo.Entity.TestCase;
import com.sds.demo.Service.TestCaseService;
import com.sds.demo.VO.BaseListVO;

import com.sds.demo.VO.TestCaseVO;

import com.sds.demo.VO.TestResultDetailVO;
import com.sds.demo.converter.TestCaseConverter;
import com.sds.demo.dao.ComponentMapper;
import com.sds.demo.dao.TestCaseMapper;
import com.sds.demo.util.IperfCommand;
import com.sds.demo.util.ResultHandle;
import com.sds.demo.util.SSHConnection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @Author Voidmian
 * @Date 2021/1/28 16:49
 */
@Service
public class TestCaseImpl implements TestCaseService {
    private final TestCaseMapper testCaseMapper;
    private final ComponentMapper componentMapper;
    @Value("${Iperf.A.ip}")
    String hostA;
    @Value("${Iperf.A.port}")
    int portA;
    @Value("${Iperf.A.username}")
    String userA;
    @Value("${Iperf.A.password}")
    String passwordA;

    @Value("${Iperf.C.ip}")
    String hostC;
    @Value("${Iperf.C.port}")
    int portC;
    @Value("${Iperf.C.username}")
    String userC;
    @Value("${Iperf.C.password}")
    String passwordC;

    @Autowired
    private IperfServiceImpl iperfService;
    @Autowired
    private DataService dataService;
    @Autowired
    private TestResultImpl testResult;

    public TestCaseImpl(TestCaseMapper testCaseMapper, ComponentMapper componentMapper) {
        this.testCaseMapper = testCaseMapper;
        this.componentMapper = componentMapper;
    }

    public BaseListVO<TestCaseVO> getAllComponentPage(Integer pageSize, Integer pageIndex) {
        BaseList<TestCase> baseList = new BaseList<>(pageSize, pageIndex);
        baseList.setList(testCaseMapper.getAllPage(baseList.getPageSize(), baseList.getOffset()));
        return TestCaseConverter.convertListDV(baseList);
    }

    public String insert(TestCaseVO testCaseVO) {
        TestCase testCase = TestCaseConverter.convertVD(testCaseVO);
        testCaseMapper.insertTestCase(testCase);
        return "ok";
    }

    public String update(TestCaseVO testCaseVO) {
        TestCase testCase = TestCaseConverter.convertVD(testCaseVO);
        testCaseMapper.updateTestCase(testCase);
        return "ok";
    }

    public String delete(int id) {
        testCaseMapper.deleteTestCase(id);
        return "ok";
    }

    public TestResultDetailVO startCase(String name) {
        try {
            TestCase testCase = testCaseMapper.getOneByName(name);
            //:TODO 取一个
            Component component = componentMapper.getOneByName(name);
            //:TODO 获取组件命令
            String commandComponent = component.getCommand() + "\n";
            //:TODO B的连接数据修改
            SSHConnection connectionB = new SSHConnection(hostC, portC, userC, passwordC);
            connectionB.exeCommand(commandComponent);

            IperfParams iperfParams = new IperfParams(testCase.getProtocol(), testCase.getTotalPackages(), testCase.getTimeSlot().toString(),
                    testCase.getBandwidthLimit(), testCase.getTotalPackages(), testCase.getBufferLength(), testCase.getBidirectionalTest(),
                    testCase.getTCPWindow(), testCase.getMss(), testCase.getIpv4_6());

            String commandB = IperfCommand.startCCommand(testCase.getName() + "C");
            String commandA = IperfCommand.startACommand(testCase.getName() + "A", iperfParams);
            SSHConnection connectionA = new SSHConnection(hostA, portA,userA, passwordA);

            String[] commandsB = new String[3];
            commandsB[0] = "docker exec " + component.getName() + "\n";
            commandsB[1] = commandB + "\n";
            commandsB[2] = "docker cp " + component.getName() + ":" + IperfCommand.remoteAddr + "\\" + testCase.getName() + "C" + " \\home";
            connectionA.exeCommand(commandA);
            connectionB.exeCommands(commandsB);

            String localPathB = "\\home\\result\\"+ testCase.getName() + "C";
            String localPathA = "\\home\\result\\"+ testCase.getName() + "A";
            String remotePathA = "\\home";
            String remoteFileNameA = testCase.getName() + "A.txt";
            String remotePathB = "\\home";
            String remoteFileNameB = testCase.getName() + "C.txt";
            connectionB.download(localPathB, remotePathB, remoteFileNameB);
            connectionA.download(localPathA, remotePathA, remoteFileNameA);

            Data dataA = dataService.getDataFromFile(localPathA, testCase.getName() + "A.txt");
            Data dataB = dataService.getDataFromFile(localPathB, testCase.getName() + "C.txt");

            //: TODO 重新编写result服务
            testResult.insert(dataA);
            testResult.insert(dataB);


            //ResultHandle resultHandle = new ResultHandle();
            //resultHandle.handle(localPath);
            return new TestResultDetailVO(testCase.getId(), resultHandle.getTransRate(), resultHandle.getTransScale());
        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }
    }

}
