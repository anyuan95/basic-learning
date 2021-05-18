package org.example.juc.interview.i2_AlternatelyPrint;

import java.util.concurrent.locks.LockSupport;

/**
 * @author anyuan
 * @since 2021-05-18 14:57
 */
public class Resolution_LockSupport {

    static Thread t1 = null, t2 = null;

    public static void main(String[] args) {

        t1 = new Thread(() -> {
            for (int i = 'A'; i <= 'Z'; i++) {
                System.out.print(((char) i));
                LockSupport.unpark(t2);
                LockSupport.park();
            }
            LockSupport.unpark(t2);
        });
        t2 = new Thread(() -> {
            LockSupport.park();
            for (int i = 1; i <= 26; i++) {
                System.out.print(i);
                LockSupport.unpark(t1);
                LockSupport.park();
            }
        });

        t1.start();
        t2.start();
    }
}
