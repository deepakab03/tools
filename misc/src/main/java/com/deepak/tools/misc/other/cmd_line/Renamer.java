package com.deepak.tools.misc.other.cmd_line;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Renamer {
    private static final Logger logger = LoggerFactory.getLogger(Renamer.class);

    public static void main(String[] args) throws IOException {
        listFileNames();
//        renameRemoving0FromXsd();
    }

    private static void listFileNames() throws IOException {
        String str = Files.list(Paths.get("<location>\\src\\main\\resources\\xsd\\sub1\\sub2"))
            .map(path -> "src\\main\\resources\\xsd\\sub1\\sub2\\" + path.getFileName().toString())
            .collect(Collectors.joining(" "));
        logger.info("File names: {}", str);
    }

    public static void renameRemoving0FromXsd() throws IOException {
        Files.list(Paths.get("<location>")).
            filter(path -> path.getFileName().toString().endsWith("xsd")).forEach( path -> {
                String nameWithoutExt = com.google.common.io.Files.getNameWithoutExtension(path.getFileName().toString());
                String newFileName = nameWithoutExt.substring(0, nameWithoutExt.length() - 1);
                File renamedFile = Paths.get(path.getParent().toAbsolutePath().toString() + File.separator + newFileName + ".xsd").toFile();
                logger.info("File orig: {}, new {}", nameWithoutExt, renamedFile.getAbsolutePath());
                path.toFile().renameTo(renamedFile);
            });
    }
}
