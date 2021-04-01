package com.sds.demo.util;

import com.sds.demo.Entity.Component;

/**
 * @ClassName: SSHConnection
 * @Description: TODO
 * @author: XQC
 * @date: 2021/4/1  8:30
 */
public class SSHConnection {
    public void sshCommand(String[] command) {
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
}
