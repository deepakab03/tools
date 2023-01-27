package com.deepak.tools.misc.other.cmd_line;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

import org.junit.Test;


public class CmdLineExecutorTest {

    private CmdLineExecutor cmdLineExecutor = new CmdLineExecutor();
    
    @Test public void 
    whenExecuteCmd1_shouldDoCorrectly() throws IOException, InterruptedException {
        int exitValue = cmdLineExecutor.executeCmd1(new String[] {"cmd", "/c", "cd", "C:\\dev", "&&", "dir"});
        
        assertThat(exitValue, is(0));
    }
    
    @Test public void 
    whenExecuteCmd2_shouldCaptureOutput() throws IOException, InterruptedException {
        CmdExecResult result = cmdLineExecutor.executeCmd2(new String[] {"cmd", "/c", "cd", "C:\\dev", "&&", "dir"});
        
        assertThat(result.getExitCode(), is(0));
    }
    
    @Test public void 
    whenExecuteCmd3_doItAsyncAndCaptureOutput() throws IOException, InterruptedException, ExecutionException {
        int exitValue = cmdLineExecutor.executeCmd3(new String[] {"cmd", "/c", "cd", "C:\\dev", "&&", "dir"});
        
        assertThat(exitValue, is(0));
    }
}
