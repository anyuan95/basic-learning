package org.example.jvm.invoke;

import cn.hutool.core.io.LineHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author anyuan
 * @since 2021-06-02 14:51
 */
public class TestLambda {

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
//        list.forEach(System.out::println);
        final int size = list.size();

        final ArrayList<String> arrayList = new ArrayList<>();
        final int size1 = arrayList.size();

//        final Consumer consumer = System.out::println;
    }
}