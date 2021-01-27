package com.sds.demo.util;



import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ClientChannel;
import org.apache.sshd.client.channel.ClientChannelEvent;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.common.channel.Channel;
import org.apache.sshd.common.util.io.NoCloseInputStream;
import org.apache.sshd.common.util.io.NoCloseOutputStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.EnumSet;
import java.util.concurrent.TimeUnit;


/**
 * @Author Voidmian
 * @Date 2021/1/26 15:33
 */
public class SshCommand {
    String host;
    int port;
    String user;
    String password;
    long defaultTimeoutSeconds = 10;

    public SshCommand(String host, int port, String user, String password) {
        this.host = host;
        this.password = password;
        this.port = port;
        this.user = user;
    }

    public String exeCommand(String [] commands) throws IOException {
        SshClient client = SshClient.setUpDefaultClient();
        client.start();

        try (ClientSession session = client.connect(user, host, port).verify(defaultTimeoutSeconds, TimeUnit.SECONDS).getSession()) {
            session.addPasswordIdentity(password);
            session.auth().verify(defaultTimeoutSeconds, TimeUnit.SECONDS);

            try (ByteArrayOutputStream responseStream = new ByteArrayOutputStream();
                 ClientChannel channel = session.createChannel(Channel.CHANNEL_SHELL)) {
                channel.setOut(responseStream);
                try {
                    channel.open().verify(defaultTimeoutSeconds, TimeUnit.SECONDS);
                    try (OutputStream pipedIn = channel.getInvertedIn()) {
                        for (String command:commands
                             ) {
                            pipedIn.write(command.getBytes());
                            pipedIn.flush();
                            channel.waitFor(EnumSet.of(ClientChannelEvent.STDOUT_DATA),
                                    TimeUnit.SECONDS.toMillis(defaultTimeoutSeconds));
                        }
                    }
                    String responseString = new String(responseStream.toByteArray());
                    System.out.println(responseString);
                } finally {
                    channel.close(false);
                }
            }
        } finally {
            client.stop();
        }
        return null;
    }
}
