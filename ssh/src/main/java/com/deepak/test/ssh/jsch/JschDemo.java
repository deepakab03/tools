package com.deepak.test.ssh.jsch;

import static org.apache.commons.lang3.StringUtils.isBlank;

import java.io.File;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.deepak.test.ssh.exception.JschRuntimeException;
import com.deepak.test.ssh.model.FileTransferDetails;
import com.deepak.test.ssh.model.RemoteConnectionParams;
import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

public class JschDemo {
    private static final Logger logger = LoggerFactory.getLogger(JschDemo.class);

    private Properties config = new Properties();
    private final String knownHostsFile;

    public JschDemo() {
        this(true);
    }

    /**
     * Rather than pass true, better to use <i> ssh-keyscan <host> >> ~/.ssh/known_hosts </i> and specifically ensure
     * known_hosts file is added with the host that you need (after verifying that the fingerprint returned by
     * <i>ssh-keyscan <host-name></i> is valid
     * 
     * @param strictHostChecking - preferably false, true can lead to security vulnerabilities
     */
    public JschDemo(boolean strictHostChecking) {
        this(strictHostChecking, "");
    }

    /**
     * Rather than pass true, better to use <i> ssh-keyscan <host> >> ~/.ssh/known_hosts </i> and specifically ensure
     * known_hosts file is added with the host that you need (after verifying that the fingerprint returned by
     * <i>ssh-keyscan <host-name></i> is valid
     * 
     * @param strictHostChecking - preferably false, true can lead to security vulnerabilities
     * @param strictHostChecking -
     */
    public JschDemo(final boolean strictHostChecking, final String knownHostsFilePassedIn) {
        if (!strictHostChecking) {
            /*
             * Following gets around the UnknownHostKey exception when the knownHosts file doesn't have the fingerprint,
             * but is UNSECURE and should NOT be used except for testing (or as a first case use). HOWEVER setting this
             * property ensures that JSch will add new entries to that file (when Strict Host Key Check is set to 'no')
             */
            config.put("StrictHostKeyChecking", "no");
        }

        String knownHostsFile = knownHostsFilePassedIn;
        if (!isBlank(knownHostsFile)) {
            // generally ~/.ssh/known_hosts in linux and windows where// ~ is user home dir
            String userHome = System.getProperty("user.home");
            Objects.requireNonNull(userHome);
            knownHostsFile = userHome + File.separator + ".ssh" + File.separator + "known_hosts";
        }
        this.knownHostsFile = knownHostsFile;
        logger.info("Using known host file {}", knownHostsFile);
    }

    public boolean sftpGet(RemoteConnectionParams connectionParams, FileTransferDetails fileTransferDetails)
            throws IOException {
        Session session = null;
        Channel channel = null;
        try {
            //Ambiguous whether JSch is thread safe or not
            JSch ssh = new JSch();

            session = ssh.getSession(connectionParams.getUserName(), connectionParams.getHostName(),
                    connectionParams.getHostPort());
            session.setPassword(connectionParams.getPwd());
            ssh.setKnownHosts(knownHostsFile);
            session.setConfig(config);

            session.connect();
            channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp sftp = (ChannelSftp) channel;
            logger.debug("Copying from remote {} into local {}", fileTransferDetails.getRemoteFilePath(),
                    fileTransferDetails.getLocalFilePathToCopyTo());
            sftp.get(fileTransferDetails.getRemoteFilePath(), fileTransferDetails.getLocalFilePathToCopyTo());

            return true;
        } catch (JSchException e) {
            throw new JschRuntimeException("Failed in connecting / fetching data from: " + connectionParams, e);
        } catch (SftpException e) {
            throw new JschRuntimeException("Failed in fetching data from: " + connectionParams, e);
        } finally {
            if (channel != null) {
                channel.disconnect();
            }
            if (session != null) {
                session.disconnect();
            }
        }
    }

}
