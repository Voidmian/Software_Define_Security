package com.sds.demo.Service.Impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.sds.demo.Entity.BaseList;

import com.sds.demo.Entity.Component;
import com.sds.demo.Entity.TestResult;
import com.sds.demo.Entity.DataD;
import com.sds.demo.Entity.IperfParams;
import com.sds.demo.Entity.TestCase;
import com.sds.demo.Entity.TestResult;
import com.sds.demo.Service.TestCaseService;
import com.sds.demo.VO.BaseListVO;
import com.sds.demo.VO.TestCaseVO;
import com.sds.demo.converter.TestCaseConverter;
import com.sds.demo.dao.ComponentMapper;
import com.sds.demo.dao.TestCaseMapper;
import com.sds.demo.util.GetData;
import com.sds.demo.util.IperfCommand;
import com.sds.demo.util.SSHConnection;
import com.sds.demo.util.TimeUtil;
import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Voidmian
 * @Date 2021/1/28 16:49
 */
@Service
public class TestCaseImpl implements TestCaseService {
    @Autowired
    private TestCaseMapper testCaseMapper;
    @Autowired
    private ComponentMapper componentMapper;
    @Value("${Iperf.A.ip}")
    String hostA;
    @Value("${Iperf.A.port}")
    int portA;
    @Value("${Iperf.A.username}")
    String userA;
    @Value("${Iperf.A.password}")
    String passwordA;
    @Value("${Iperf.remoteA}")
    String remoteA;

    @Value("${Iperf.B.ip}")
    String hostB;
    @Value("${Iperf.B.port}")
    int portB;
    @Value("${Iperf.B.username}")
    String userB;
    @Value("${Iperf.B.password}")
    String passwordB;
    @Value("${Iperf.remoteB}")
    String remoteB;

    @Value("${Iperf.C.ip}")
    String hostC;
    @Value("${Iperf.C.port}")
    int portC;
    @Value("${Iperf.C.username}")
    String userC;
    @Value("${Iperf.C.password}")
    String passwordC;
    @Value("${Iperf.remoteC}")
    String remoteC;
    @Value("${localPath}")
    String localPath;

    private GetData getData = new GetData();


    @Autowired
    private TestResultImpl testResultService;


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

    public void startCase(String name) {
        try {
            IperfCommand iperfCommand = new IperfCommand();
            TestCase testCase = testCaseMapper.getOneByName(name);

            Component component = componentMapper.getOneByName(testCase.getComponentName());
            SSHConnection connectionA = new SSHConnection(hostA, portA, userA, passwordA);
            SSHConnection connectionB = new SSHConnection(hostB, portB, userB, passwordB);
            connectionB.exeCommand("docker run -idt -p 192.168.12.1:8080:8080 --name container_test" + " --privileged=true --cpus="
                    + component.getCommand() + " " + "centos6316:4.0" + " /usr/sbin/init\n");

            IperfParams iperfParams = new IperfParams(testCase.getProtocol(), testCase.getTotalTime(), testCase.getTimeSlot(),
                    testCase.getBandwidthLimit(), testCase.getTotalPackages(), testCase.getBufferLength(), testCase.getBidirectionalTest(),
                    testCase.getTCPWindow(), testCase.getMss(), testCase.getIpv4_6());

            String commandC = iperfCommand.startCCommand(testCase.getName() + "C");
            String commandA = iperfCommand.startACommand(testCase.getName() + "A", iperfParams);

            String commandB = "docker exec -it container_test /bin/bash\n" + commandC;
            connectionB.exeCommand(commandB);
            connectionA.exeCommand(commandA);
            System.out.println(commandA);
            System.out.println("===============A上执行命令============");

            Thread.sleep(iperfParams.getTotalTime() * 1200);
            System.out.println("===============等待结束============");

            connectionB.exeCommand("docker cp container_test" + ":"  + "/iperf3_file/" + testCase.getName() + "C.txt " + remoteB + "\n");
            System.out.println("===============拷贝到本机============");
            connectionB.exeCommand("docker kill container_test\n");
            System.out.println("===============杀死容器============");
            connectionB.exeCommand("docker rm container_test\n");
            System.out.println("===============删除容器============");

            String localPathB = localPath + "/" + testCase.getName() + "C.txt";
            String localPathA = localPath + "/" + testCase.getName() + "A.txt";
            String remoteFileNameA = testCase.getName() + "A.txt";
            String remoteFileNameB = testCase.getName() + "C.txt";
            System.out.println("localPathA："+localPathA);
            System.out.println("localPathB："+localPathB);
            connectionB.download(localPathB, remoteB, remoteFileNameB);
            System.out.println("===============B上下载============");
            connectionA.download(localPathA, remoteA, remoteFileNameA);
            System.out.println("===============A上下载============");

            TestResult dataA = getData.getDataFromFile(localPath, testCase.getName() + "A.txt");
            TestResult dataB = getData.getDataFromFile(localPath, testCase.getName() + "C.txt");

            DataD dA = new DataD(dataA.getCaseName(), JSON.toJSONString(dataA, SerializerFeature.WriteMapNullValue));
            DataD dB = new DataD(dataB.getCaseName(), JSON.toJSONString(dataB, SerializerFeature.WriteMapNullValue));
            dA.setDate(TimeUtil.now());
            dB.setDate(TimeUtil.now());


            testResultService.insert(dA);
            testResultService.insert(dB);


        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @Override
    public List<TestCase> getAll() {
        return testCaseMapper.getAll();
    }

}
