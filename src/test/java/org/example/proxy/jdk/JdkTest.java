package org.example.proxy.jdk;

import java.lang.reflect.Proxy;

/**
 * @author anyuan
 * @since 2021-05-11 10:12
 */
public class JdkTest {

    interface Runnable {
        String run(Double milePerSecond);
    }

    public static final class User implements Runnable {
        public String greet() {
            System.out.println("hello!");
            return "hello";
        }

        @Override
        public String run(Double milePerSecond) {
            System.out.println("i can run " + milePerSecond + " m/s");
            return "i can run " + milePerSecond + " m/s";
        }
    }

    public static void main(String[] args) {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        System.getProperties().put("jdk.proxy.ProxyGenerator.saveGeneratedFiles", "true");

        final User user = new User();
        Runnable runnable = (Runnable) Proxy.newProxyInstance(User.class.getClassLoader(), new Class[]{Runnable.class}, (proxy, method, args1) -> {
            System.out.println("start");
            final Object result = method.invoke(user, args1);
            System.out.println("end");
            return result;
        });
        runnable.run(1D);
    }
}
