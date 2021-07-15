package org.example.jvm.oom;

import java.util.function.Supplier;

/**
 * @author anyuan
 * @since 2021-06-07 11:28
 */
public class TestLambda {

    public static void main(String[] args) {
        for (; ; ) {
//            Supplier s = () -> 1;
            A a = () -> {};
        }
    }

    public interface A {
        void a();
    }

//    public static class B implements A {
//        @Override
//        public void a() {
//        }
//    }
}
