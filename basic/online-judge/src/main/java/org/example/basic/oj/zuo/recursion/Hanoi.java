package org.example.basic.oj.zuo.recursion;

/**
 * @author anyuan
 * @since 2021-08-17 10:44
 */
public class Hanoi {

    public void hanoi(int n) {
//        leftToRight(n);
        move(n, "left", "right", "helper");
    }

    private void move(int n, String from, String to, String helper) {
        if (n == 1) {
            System.out.println("move 1 from " + from + " to " + to);
            return;
        }
        move(n - 1, from, helper, to);
        System.out.println("move " + n + " from left to right");
        move(n - 1, helper, to, from);
    }

    private void leftToRight(int n) {
        if (n == 1) {
            System.out.println("move 1 from left to right");
            return;
        }
        leftToMid(n - 1);
        System.out.println("move " + n + " from left to right");
        midToRight(n - 1);
    }

    private void midToRight(int n) {
        if (n == 1) {
            System.out.println("move 1 from mid to right");
            return;
        }
        midToLeft(n - 1);
        System.out.println("move " + n + " from mid to right");
        leftToRight(n - 1);
    }

    private void midToLeft(int n) {
        if (n == 1) {
            System.out.println("move 1 from mid to left");
            return;
        }
        midToRight(n - 1);
        System.out.println("move " + n + " from mid to left");
        rightToLeft(n - 1);
    }

    private void rightToLeft(int n) {
        if (n == 1) {
            System.out.println("move 1 from right to left");
            return;
        }
        rightToMid(n - 1);
        System.out.println("move " + n + " from right to left");
        midToLeft(n - 1);
    }

    private void rightToMid(int n) {
        if (n == 1) {
            System.out.println("move 1 from right to mid");
            return;
        }
        rightToLeft(n - 1);
        System.out.println("move " + n + " from right to mid");
        leftToMid(n - 1);
    }

    private void leftToMid(int n) {
        if (n == 1) {
            System.out.println("move 1 from left to mid");
            return;
        }
        leftToRight(n - 1);
        System.out.println("move " + n + " from left to mid");
        rightToMid(n - 1);
    }

    public static void main(String[] args) {
        final Hanoi hanoi = new Hanoi();
        hanoi.leftToRight(10);
    }
}
