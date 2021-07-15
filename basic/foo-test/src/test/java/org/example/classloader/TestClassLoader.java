package org.example.classloader;

import org.junit.runners.model.TestClass;

/**
 * @author anyuan
 * @since 2021-05-26 14:18
 */
public class TestClassLoader {

    public static void main(String[] args) throws ClassNotFoundException {
//        System.out.println(ClassLoader.class.getClassLoader());
        final ClassLoader classLoader = TestClassLoader.class.getClassLoader();
        // ClassNotFoundException
        final Class<?> aClass = classLoader.loadClass("org.example.classloader.nonexistent.Myclazz");
        System.out.println(aClass);
    }
}
