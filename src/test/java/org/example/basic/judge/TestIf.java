package org.example.basic.judge;

/**
 * @author anyuan
 * @since 2021-05-19 22:12
 */
public class TestIf {

    public static void main(String[] args) {
        int a = (int) (System.currentTimeMillis() % 10);
        if (a != 1) {
            System.out.println("not 1");
        } else {
            System.out.println("1");
        }
    }
}
