package org.example.reference;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * @author anyuan
 * @since 2021-05-19 10:20
 */
public class TestWeak {

    public static void main(String[] args) throws InterruptedException {
        User u = new User();
        final WeakReference<User> reference = new WeakReference<>(u);

        System.out.println(reference.get());
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(reference.get());

        u = null;
        System.gc();
        TimeUnit.SECONDS.sleep(1);
        System.out.println(reference.get());
    }

    static class User {
        @Override
        protected void finalize() throws Throwable {
            System.out.println("finalized!");
            super.finalize();
        }
    }
}
