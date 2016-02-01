package com.deepak.tools.powermock;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertThat;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.deepak.tools.junit_etc.SomeIdentifier;

@RunWith(PowerMockRunner.class)
@PrepareForTest(InetAddress.class)
public class InetAddressPowerMockTest {

	private static final String TEST_APP_NAME1 = "abc_client";
	
    @Mock private InetAddress inetAddress;
    
    //THIS DOESN'T WORK.. some incompatiblity with mockito
    @Test public void
    whenEquals_given2ObjectsWithSameaPPNamesFromDifferentHosts_shouldReturnFalse() throws UnknownHostException {
    	PowerMockito.spy(InetAddress.class);
        Mockito.when(InetAddress.getLocalHost()).thenReturn(inetAddress);
        Mockito.when(inetAddress.getHostName()).thenReturn("abc_host").thenReturn("xyz_host");
        SomeIdentifier clientIdentifier1 = new SomeIdentifier(TEST_APP_NAME1);
        SomeIdentifier clientIdentifier2 = new SomeIdentifier(TEST_APP_NAME1);
        
        assertThat(clientIdentifier1, is(not(equalTo(clientIdentifier2))));
        
        PowerMockito.verifyStatic();
        InetAddress.getLocalHost();
    }
}
