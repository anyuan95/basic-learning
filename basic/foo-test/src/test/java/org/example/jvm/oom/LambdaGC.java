package org.example.jvm.oom;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LambdaGC {
    public static void main(String[] args) {
        List<I> lists = new ArrayList<>();
        for (int i = 5; i > 0; i--) {
//            I ix = C::n;
            I ix = () -> {
                System.out.println(new Random().nextInt());
            };
            lists.add(ix);
            System.out.println(ix.getClass());
        }
    }

    public static interface I {
        void m();
    }

    public static class C {
        static void n() {
            System.out.println("hello");
        }
    }
    // -XX:MaxMetaspaceSize=9M -XX:+PrintGCDetails
}