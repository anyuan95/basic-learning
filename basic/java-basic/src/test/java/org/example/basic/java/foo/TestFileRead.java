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

    @Test
    public void test() {
        final byte[] bytes = {-78, 0, 96, -58, 0, 17, -78, 0, 96, -74, 0, 101, -64, 0, 91, 89, 75, -57, 0, 18, -72, 0, 103, 75, -69, 0, 98, 89, 42, -73, 0, 106, -77, 0, 96, 42, -76, 0, 110, -80, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        FileUtil.writeBytes(bytes, "/Users/anyuan/Downloads/Codings/groovy/test/Script_lastMethod.class");
//        System.out.println(new String(bytes));
    }

}
