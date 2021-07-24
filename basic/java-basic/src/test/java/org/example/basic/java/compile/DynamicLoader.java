package org.example.basic.java.compile;

import javax.tools.JavaCompiler;
import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.ToolProvider;
import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Required JDK >= 1.6<br><br>
 * This class can help you create the Java byte code dynamically through the string and load it into memory.<br><br>
 * <p>
 * HOW TO:<br>
 * First step. <code>Map<String, byte[]> bytecode = DynamicLoader.compile("TestClass.java", javaSrc);</code><br>
 * Second step. <code>DynamicLoader.MemoryClassLoader classLoader = new DynamicLoader.MemoryClassLoader(bytecode);</code><br>
 * Third step. <code>Class clazz = classLoader.loadClass("TestClass");</code><br>
 * <br>
 * Then just like the normal use of the call this class can be.
 */
public class DynamicLoader {
    /**
     * auto fill in the java-name with code, return null if cannot find the public class
     *
     * @param javaSrc source code string
     * @return return the Map, the KEY means ClassName, the VALUE means bytecode.
     */
    public static Map<String, byte[]> compile(String javaSrc) {
        Pattern pattern = Pattern.compile("public\\s+class\\s+(\\w+)");

        Matcher matcher = pattern.matcher(javaSrc);

        if (matcher.find())
            return compile(matcher.group(1) + ".java", javaSrc);
        return null;
    }

    /**
     * @param javaName the name of your public class,eg: <code>TestClass.java</code>
     * @param javaSrc  source code string
     * @return return the Map, the KEY means ClassName, the VALUE means bytecode.
     */
    public static Map<String, byte[]> compile(String javaName, String javaSrc) {
        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        StandardJavaFileManager stdManager = compiler.getStandardFileManager(null, null, null);

        try (MemoryJavaFileManager manager = new MemoryJavaFileManager(stdManager)) {
            JavaFileObject javaFileObject = MemoryJavaFileManager.makeStringSource(javaName, javaSrc);
            JavaCompiler.CompilationTask task = compiler.getTask(null, manager, null,
                    null, null, Collections.singletonList(javaFileObject));
            if (task.call()) {
                return manager.getClassBytes();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static class MemoryClassLoader extends URLClassLoader {

        Map<String, byte[]> classBytes = new HashMap<String, byte[]>();

        public MemoryClassLoader(Map<String, byte[]> classBytes) {
            super(new URL[0], MemoryClassLoader.class.getClassLoader());
            this.classBytes.putAll(classBytes);
        }

        @Override
        protected Class<?> findClass(String name) throws ClassNotFoundException {
            byte[] buf = classBytes.get(name);
            if (buf == null) {
                return super.findClass(name);
            }
            classBytes.remove(name);
            return defineClass(name, buf, 0, buf.length);
        }
    }
}

