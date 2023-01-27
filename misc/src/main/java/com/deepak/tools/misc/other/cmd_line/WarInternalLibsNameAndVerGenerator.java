package com.deepak.tools.misc.other.cmd_line;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * To generate output of File name dash ver of jar files given a folder - the exploded contents of a war, ear, or spring
 * integrated jar that has libs in it
 * 
 * @author abrahd2
 *
 */
public class WarInternalLibsNameAndVerGenerator {


  private static final String VER_INDEX_CHAR = "-";

  public static void main(String[] args) throws IOException, InterruptedException {
    CmdLineExecutor cmdLine = new CmdLineExecutor();
    CmdExecResult execResult = cmdLine.executeCmd2(new String[] {"dir", args[0]});

    List<String> output = execResult.getOutput();

    List<String> modifiedOutput = new ArrayList<>(output.size());
    for (String line : output) {
      boolean lineIsLib = line.endsWith(".jar") || line.endsWith("zip");
      if (lineIsLib) {
        boolean snapshotVer = line.contains("SNAPSHOT");
        int verSeparatorIndex = -1;
        if (snapshotVer) {
          verSeparatorIndex = line.substring(0, line.lastIndexOf("-SNAPSHOT")).lastIndexOf(VER_INDEX_CHAR);
        } else {
          verSeparatorIndex = line.lastIndexOf(VER_INDEX_CHAR);
        }
        String lib = line;
        String ver = "";
        if (verSeparatorIndex != -1) {
          lib = line.substring(0, verSeparatorIndex);
          ver = line.substring(verSeparatorIndex + 1, line.lastIndexOf("."));
        }
        String csvLine = lib + "," + ver;
        System.out.println(csvLine);
        modifiedOutput.add(csvLine);
      }
    }
    Path writtenPath = Files.write(Paths.get("./temp-data.csv"), modifiedOutput);

    System.out.println("Wrote csvt to " + writtenPath);
  }
}
