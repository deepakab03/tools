package com.deepak.test.ssh.jsch;

import static org.apache.commons.io.FileUtils.readFileToString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.nio.file.Paths;

import org.junit.Test;

import com.deepak.test.ssh.model.FileTransferDetails;
import com.deepak.test.ssh.model.RemoteConnectionParams;


public class JschDemoIT {

    private JschDemo jschDemo = new JschDemo(false);
    
    @Test public void
    whenSftpGet_shouldGetFileFromRemote() throws IOException {
        RemoteConnectionParams connectionParams = new RemoteConnectionParams();
        connectionParams.setHostName("xyz.server");
        connectionParams.setUserName("xyzuser");
        connectionParams.setPwd("xyzhost");
        FileTransferDetails fileTransferDetails = new FileTransferDetails();
        fileTransferDetails.setRemoteFilePath("/home/blah/blah_notification.xml");
        String localFilePathToCopyTo = "c:\\dev\\t\\local_copy.xml";
        fileTransferDetails.setLocalFilePathToCopyTo(localFilePathToCopyTo);
        if (Paths.get(localFilePathToCopyTo).toFile().exists()) {
            assertThat(readFileToString(Paths.get(localFilePathToCopyTo).toFile()), is(""));
        }
       
        boolean success = jschDemo.sftpGet(connectionParams, fileTransferDetails);
        
        assertThat(success, is(true));
        assertThat(readFileToString(Paths.get(localFilePathToCopyTo).toFile()), is(not("")));
    }
 
}
