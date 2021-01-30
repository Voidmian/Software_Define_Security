package com.sds.demo.util;



import com.sds.demo.Entity.TestCase;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.sshd.client.SshClient;
import org.apache.sshd.client.channel.ClientChannel;
import org.apache.sshd.client.channel.ClientChannelEvent;
import org.apache.sshd.client.session.ClientSession;
import org.apache.sshd.client.subsystem.sftp.SftpClient;
import org.apache.sshd.client.subsystem.sftp.SftpClientFactory;
import org.apache.sshd.client.subsystem.sftp.fs.SftpFileSystem;
import org.apache.sshd.common.channel.Channel;
import org.apache.sshd.common.util.io.NoCloseInputStream;
import org.apache.sshd.common.util.io.NoCloseOutputStream;

import java.io.*;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    static public SshCommand getInstance(){
        String host = "47.98.228.148";
        int port = 22;
        String username = "root";
        String password = "yjj0413_Aly";
        return new SshCommand(host, port, username, password);
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

    public String copy(String localPath,String remotePath,String remoteFileName) throws IOException {
        SshClient client = SshClient.setUpDefaultClient();
        client.start();
        try (ClientSession session = client.connect(user, host, port).verify().getSession())
        {
            session.addPasswordIdentity(password);
            session.auth().verify(defaultTimeoutSeconds, TimeUnit.SECONDS);
            SftpFileSystem sftp = SftpClientFactory.instance().createSftpFileSystem(session);
            Path remote = sftp.getDefaultDir().resolve(remotePath);
            if (!Files.exists(remote)) {
                Files.createDirectories(remote);
            }
            Path remoteFile = remote.resolve(remoteFileName);
            Files.deleteIfExists(remoteFile);
            Files.copy(Paths.get(localPath), remoteFile);
            session.close(false);
        }  catch (Exception e){
            System.out.println(e.toString());
        }
        finally {
            client.stop();
        }
        return  null;
    }

    public String download(String localPath,String remotePath,String remoteFileName) {
        SshClient client = SshClient.setUpDefaultClient();
        client.start();
        try (ClientSession session = client.connect(user, host, port).verify().getSession())
        {
            session.addPasswordIdentity(password);
            session.auth().verify(defaultTimeoutSeconds, TimeUnit.SECONDS);
            SftpFileSystem sftp = SftpClientFactory.instance().createSftpFileSystem(session);
            Files.deleteIfExists(Paths.get(localPath));
            Path remoteFile = sftp.getDefaultDir().resolve(remotePath).resolve(remoteFileName);
            Files.copy(remoteFile,Paths.get(localPath));
            session.close(false);
        }  catch (Exception e){
            System.out.println(e.toString());
        }
        finally {
            client.stop();
        }
        return  null;
    }

    public String startTestCase(TestCase testCase) {
        return null;
    }
}
