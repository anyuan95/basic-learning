package org.example.clazz;

/**
 * @author anyuan
 * @since 2021-05-27 22:11
 */
public class TestExtend {

    public static void main(String[] args) {
        Father father = new Father();
//        final Son son = new Son(1);
    }

    static class Father {
        public Father() {
            System.out.println("i m father");
        }
    }

    static class Son extends Father {
        public Son(int a) {
            System.out.println("i m son");
        }
    }
}
