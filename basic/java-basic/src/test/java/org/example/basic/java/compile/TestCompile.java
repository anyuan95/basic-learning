package org.example.basic.java.compile;

import cn.hutool.core.io.FileUtil;
import org.junit.Test;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author anyuan
 * @since 2021-07-20 09:47
 */
public class TestCompile {

    private static String filePath = "E:\\Programming\\Workspaces\\IDEA\\Tests\\spring-learning\\basic-learning\\basic\\java-basic\\src\\main\\external\\Test.java";

    @Test
    public void testInvoke() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        final String classFileContent = new String(FileUtil.readBytes(filePath), StandardCharsets.UTF_8);
        final Map<String, byte[]> byteCode = DynamicLoader.compile(classFileContent);
        final DynamicLoader.MemoryClassLoader memoryClassLoader = new DynamicLoader.MemoryClassLoader(byteCode);
        final Class<?> clazz = memoryClassLoader.loadClass("Test");
        final Method mainMethod = clazz.getMethod("main", String[].class);
        final Object result = mainMethod.invoke(null, (Object) new String[0]);
        System.out.println(result);
    }

    @Test
    public void compileClassFile() {
        //获取系统Java编译器
        final JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        //获取Java文件管理器
        final StandardJavaFileManager fileManager = javaCompiler.getStandardFileManager(null, null, null);
        try (final MemoryJavaFileManager memoryJavaFileManager = new MemoryJavaFileManager(fileManager)){
//            MemoryJavaFileManager.makeStringSource()
        } catch (IOException e) {
            e.printStackTrace();
        }
        //通过源文件获取到要编译的Java类源码迭代器，包括所有内部类，其中每个类都是一个 JavaFileObject，也被称为一个汇编单元
        final Iterable<? extends JavaFileObject> fileObjects = fileManager.getJavaFileObjects(filePath);
        final JavaCompiler.CompilationTask task = javaCompiler.getTask(null, fileManager, null, null, null, fileObjects);
        task.call();
    }
}
