package org.example.basic.java.foo;

import cn.hutool.core.io.FileUtil;
import org.junit.Test;

import javax.tools.*;
import java.io.*;
import java.net.URI;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author anyuan
 * @since 2021-07-19 17:50
 */
public class TestFileRead {

    private static String filePath = "E:\\Programming\\Workspaces\\IDEA\\Tests\\spring-learning\\basic-learning\\basic\\java-basic\\src\\main\\external\\Test.java";


    //    @Test
    public String readFileContent() {
        final byte[] bytes = FileUtil.readBytes(filePath);
        final String classFileContent = new String(bytes, StandardCharsets.UTF_8);
        System.out.println(classFileContent);
        return classFileContent;
    }

}
