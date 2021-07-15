package org.example.basic.layout;

import org.example.disruptor.non_lambda.MyEvent;
import org.openjdk.jol.info.ClassLayout;

/**
 * @author anyuan
 * @since 2021-05-24 11:35
 */
public class TestClassLayout {

    public static void main(String[] args) {
        System.out.println(ClassLayout.parseClass(Long.class).toPrintable());
        System.out.println(ClassLayout.parseClass(MyEvent.class).toPrintable());
    }
}
