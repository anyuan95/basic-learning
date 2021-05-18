package org.example.lambda;

import cn.hutool.core.lang.func.VoidFunc0;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-05-18 10:04
 */
public class TestFinal {

    /* effectively final ? */
    static List<?> a = null;

    public static void main(String[] args) {
        a = new ArrayList<>();

        final Runnable r1 = () -> {
            System.out.println(a);
        };

        a = new LinkedList<>();

        List<?> b = null;

        b = new ArrayList<>();
        final Runnable r2 = () -> {
            /* this line throws compile exception: 从lambda 表达式引用的本地变量必须是最终变量或实际上的最终变量 */
//            System.out.println(b);
        };

    }

//    private String name;
//
//    public void m1() {
//        final Runnable r = () -> {
//            System.out.println(name);
//        };
//    }
}
