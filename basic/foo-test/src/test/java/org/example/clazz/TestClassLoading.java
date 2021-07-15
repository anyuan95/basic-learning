package org.example.clazz;

/**
 * @author anyuan
 * @since 2021-06-09 16:26
 */
public class TestClassLoading {

    public static void main(String[] args) {
        staticFunction();

    }

    static TestClassLoading testClassLoading = new TestClassLoading();

    {
//        a++;
        b++;
    }
    private static void staticFunction() {

    }
    int a = 100;
    static int b = 200;
}
