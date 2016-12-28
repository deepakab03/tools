package com.deepak.test.ssh.model;

import java.io.IOException;
import java.nio.file.Paths;

public class FileTransferDetails {

    private String remoteFilePath;
    private String localFilePathToCopyTo;

    public String getRemoteFilePath() {
        return remoteFilePath;
    }

    public void setRemoteFilePath(String remoteFilePath) {
        this.remoteFilePath = remoteFilePath;
    }

    public String getLocalFilePathToCopyTo() {
        return localFilePathToCopyTo;
    }

    public void setLocalFilePathToCopyTo(String localFilePathToCopyTo) {
        this.localFilePathToCopyTo = localFilePathToCopyTo;
    }

    public boolean doesLocalCopyExist() {
        return Paths.get(localFilePathToCopyTo).toFile().exists();
    }

    public void createLocalCopyFile() throws IOException {
        Paths.get(localFilePathToCopyTo).toFile().createNewFile();        
    }
}
