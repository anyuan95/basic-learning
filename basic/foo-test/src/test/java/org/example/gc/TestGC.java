package org.example.gc;

import java.io.IOException;

/**
 * @author anyuan
 * @since 2021-05-19 09:52
 */
public class TestGC {

    public static void main(String[] args) throws IOException {
        User user = new User();
        user = null;
        System.gc();
        System.in.read();
    }

    private static class User {
        String name;

        @Override
        protected void finalize() throws Throwable {
            System.out.println("finalized!");
        }
    }
}
