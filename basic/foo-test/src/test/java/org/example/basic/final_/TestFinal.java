package org.example.basic.final_;

import lombok.AllArgsConstructor;

import java.util.concurrent.TimeUnit;

/**
 * @author anyuan
 * @since 2021-05-21 14:53
 */
public class TestFinal {

    @AllArgsConstructor
    private static class User {
        String name;
    }

    static User USER = new User("john");

    public static void main(String[] args) throws InterruptedException {
        final Thread t1 = new Thread(() -> {
            TestFinal.USER.name = "betty";
            System.out.println("t1: " + USER.name);
        });

        final User userCopy = USER;
        TestFinal.USER = new User("alan");
        System.out.println("userCopy: " + userCopy.name);

        t1.start();
        TimeUnit.SECONDS.sleep(3);
        USER = new User("charlie");
        System.out.println("USER: " + USER.name);

        User user = new User("test");
        final Thread t2 = new Thread(() -> {
//            System.out.println(user.name);
        });
       user = new User("another test");
        System.gc();
        t2.start();
        t2.join();
    }
}
