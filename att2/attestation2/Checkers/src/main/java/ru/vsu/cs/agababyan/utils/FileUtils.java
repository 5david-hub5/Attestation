package ru.vsu.cs.agababyan.utils;

import java.io.File;
import java.io.InputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FileUtils {

    public static final char sep = File.separatorChar;

    public static InputStream getInputStreamFromResources(String filename) {
        ClassLoader classLoader = FileUtils.class.getClassLoader();
        return classLoader.getResourceAsStream(filename);
    }


}
