package org.example.juc.interview.i2_AlternatelyPrint;

/**
 * 通过自实现的CAS方式
 *
 * @author anyuan
 * @since 2021-05-20 22:14
 */
public class Solution_CAS {

    enum Status {T1, T2}

    static volatile Status s = Status.T1;

    public static void main(String[] args) {
        final Thread t1 = new Thread(() -> {
            for (int i = 'A'; i <= 'Z'; i++) {
                while (s != Status.T1) {
                }
                System.out.print(((char) i));
                s = Status.T2;
            }
        });

        final Thread t2 = new Thread(() -> {
            for (int i = 1; i <= 26; i++) {
                while (s != Status.T2) {
                }
                System.out.print(i);
                s = Status.T1;
            }
        });

        t1.start();
        t2.start();
    }

}
