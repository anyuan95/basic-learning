package org.example.basic;

public class TestInteger {

    public static void main(String[] args) {
        Integer a = 129;
        Integer b = a;
        System.out.println(System.identityHashCode(a));
        a++;
        System.out.println(System.identityHashCode(a));
        System.out.println(a == b);
    }
}
