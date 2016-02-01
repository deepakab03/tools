package com.deepak.tools.junit_etc;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SomeIdentifier {
    private static final Logger logger = LoggerFactory.getLogger(SomeIdentifier.class);

    private final String someAppName;
    private final String theAppHostName;

    public SomeIdentifier(final String appName) {
        this.someAppName = appName;
        String localHostName = "";
        try {
            localHostName = InetAddress.getLocalHost()
                    .getHostName();
        } catch (UnknownHostException e) {
            logger.warn(e.getMessage());
        }
        this.theAppHostName = localHostName;
    }
    
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("SomeIdentifier [someAppName=");
        builder.append(someAppName);
        builder.append(", theAppHostName=");
        builder.append(theAppHostName);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((theAppHostName == null) ? 0 : theAppHostName.hashCode());
        result = prime * result + ((someAppName == null) ? 0 : someAppName.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        SomeIdentifier other = (SomeIdentifier) obj;
        if (theAppHostName == null) {
            if (other.theAppHostName != null) {
                return false;
            }
        } else if (!theAppHostName.equals(other.theAppHostName)) {
            return false;
        }
        if (someAppName == null) {
            if (other.someAppName != null) {
                return false;
            }
        } else if (!someAppName.equals(other.someAppName)) {
            return false;
        }
        return true;
    }

    public String getTheAppName() {
        return someAppName;
    }

    public String getTheAppHostName() {
        return theAppHostName;
    }

}
