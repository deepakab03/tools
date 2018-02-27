package com.deepak.tools.misc.other.cmd_line;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CmdLineExecutor {

    public int executeCmd1(String[] cmds) throws IOException, InterruptedException {
        Process process = Runtime.getRuntime()
                .exec(cmds);
        int exitValue = process.waitFor();
        boolean alive = process.isAlive();
        System.out.format("alive %s, exitValue: %d\n", alive, exitValue);

        return exitValue;
    }

    public int executeCmd2(String[] strings) throws IOException, InterruptedException {
        List<String> cmdList = Arrays.asList("cmd", "/c", "cd", "C:\\dev", "&&", "dir");
        ProcessBuilder pb = new ProcessBuilder(cmdList);
        pb.redirectErrorStream(true); // redirect STD ERR to STD OUT
        Process process = pb.start();
        try (final BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println("std-out-line: " + line);
            }
        }
        int exitValue = process.waitFor();
        System.out.format("exitValue: %d\n", exitValue);

        return exitValue;
    }

    public int executeCmd3(String[] strings) throws IOException, InterruptedException, ExecutionException {
        List<String> cmdList = Arrays.asList("cmd", "/c", "cd", "C:\\dev", "&&", "dir");
        ProcessBuilder pb = new ProcessBuilder(cmdList);
        pb.redirectErrorStream(true); // redirect STD ERR to STD OUT
        Process process = pb.start();

        final StringBuffer outputSb = new StringBuffer();
        ExecutorService executorService = null;
        try {
            executorService = Executors.newSingleThreadExecutor();
            final Future<Integer> future = executorService.submit(new Callable<Integer>() {

                @Override
                public Integer call() throws Exception {
                    try (final BufferedReader br = new BufferedReader(
                            new InputStreamReader(process.getInputStream()))) {
                        String line = null;
                        while ((line = br.readLine()) != null) {
                            outputSb.append("std-out-line: ");
                            outputSb.append(line);
                            outputSb.append('\n');
                        }
                    }
                    int exitValue = process.waitFor();
                    System.out.format("exitValue: %d\n", exitValue);

                    return exitValue;
                }
            });

            while (!future.isDone()) {
                System.out.println("Waiting for command to finish doing something else..");
                Thread.sleep(1 * 1000);
            }
            int exitValue = future.get();
            System.out.println("Output: " + outputSb);

            return exitValue;
        } finally {
            executorService.shutdown();
        }
    }

}
