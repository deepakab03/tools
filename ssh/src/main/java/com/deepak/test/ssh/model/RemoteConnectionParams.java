package com.deepak.test.ssh.model;

public class RemoteConnectionParams {

    private String hostName;
    private int hostPort = 22;
    private String userName;
    private String pwd;

    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RemoteConnectionParams [hostName=");
        builder.append(hostName);
        builder.append(", hostPort=");
        builder.append(hostPort);
        builder.append(", userName=");
        builder.append(userName);
        builder.append(", pwd=");
        builder.append(pwd);
        builder.append("]");
        return builder.toString();
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public int getHostPort() {
        return hostPort;
    }

    public void setHostPort(int hostPort) {
        this.hostPort = hostPort;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }
}
