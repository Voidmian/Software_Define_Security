package com.sds.demo.util;

import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;

/**
 * @ClassName: SSHConnectionTest
 * @Description: TODO
 * @author: XQC
 * @date: 2021/4/4  10:48
 */
public class SSHConnectionTest extends TestCase {
    SSHConnection connection = new SSHConnection("121.248.51.99", 22, "docker6316", "123456");

    public void testExeCommands() {
    }

    public void testExeCommand() {
    }
    @Test
    public void testCopy() throws IOException {
        connection.copy("C:\\Users\\productivity\\OneDrive\\图片\\Feedback\\{670CCF0E-1933-442A-B182-C54494C5F22E}\\Capture001.png", "/home/docker6316", "Capture001.png");
    }

    public void testDownload() {
    }
}