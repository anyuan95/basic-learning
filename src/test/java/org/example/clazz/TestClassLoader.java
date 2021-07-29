package org.example.clazz;

/**
 * @author anyuan
 * @since 2021-05-27 20:05
 */
public class TestClassLoader {

    public static void main(String[] args) {
        System.out.println(T.count);
    }
}

class T {
    public static T t = new T();
    public static int count = 2;

    //private int m = 8;

    private T() {
//        System.out.println("before increment: ");
//        System.out.println("count: " + count);
        count ++;
//        System.out.println("after increment: ");
//        System.out.println("count: " + count);
    }
}
