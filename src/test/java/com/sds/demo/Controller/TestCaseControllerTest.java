package com.sds.demo.Controller;

import com.sds.demo.Entity.TestCase;
import com.sds.demo.Service.Impl.TestCaseImpl;
import com.sds.demo.dao.TestCaseMapper;
import com.sds.demo.util.IperfCommand;
import com.sds.demo.util.SSHConnection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

/**
 * @ClassName: TestCaseControllerTest
 * @Description: TODO
 * @author: XQC
 * @date: 2021/4/5  15:44
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestCaseControllerTest extends TestCase {
    @Autowired
    TestCaseImpl testCaseService;
    @Autowired
    TestCaseMapper testCaseMapper;
    @Test
    public void testStartTestCase() throws IOException {
        String commandC = "iperf3 -s -p 8080 -V >testC.txt -1\n";
        SSHConnection connectionB = new SSHConnection("121.248.51.99", 22, "docker6316", "123456");
        //connectionB.exeCommand("docker load -i " + "/home/docker6316/test.tar" + "\n");
        connectionB.exeCommand("docker run -idt -p 192.168.12.1:8080:8080 --name container_test" + " --privileged=true --cpus="
               + 0.8 + " " + "centos6316:4.0" + " /usr/sbin/init\n");
        connectionB.exeCommand("docker exec -it container_test /bin/bash\n" + "echo \"helloworld\"\n"
        + commandC);
        //connectionB.exeCommand("echo \"helloworld\"\n");
        /*connectionB.exeCommand("exit\n");
        connectionB.exeCommand("docker kill container_test\n");
        connectionB.exeCommand("docker rm container_test\n");*/

    }
}