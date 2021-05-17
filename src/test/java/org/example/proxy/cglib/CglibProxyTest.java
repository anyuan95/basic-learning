package org.example.proxy.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;

import java.net.URL;
import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-05-11 09:41
 */
public class CglibProxyTest {

    public static class User {
        public String greet() {
            System.out.println("hello!");
            return "hello";
        }

        public String run(Double milePerSecond) {
            System.out.println("i can run " + milePerSecond + " m/s");
            return "i can run " + milePerSecond + " m/s";
        }
    }

    public static void main(String[] args) {
//        System.out.println(System.getProperty("user.dir"));
//        final URL resourceURL = Thread.currentThread().getContextClassLoader().getResource("");
//        System.out.println(resourceURL.getPath());
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, System.getProperty("user.dir"));
        User user = (User) Enhancer.create(User.class, (MethodInterceptor) (object, method, methodArgs, methodProxy) -> {
            System.out.println(object.getClass().getName());
//            System.out.println(Arrays.toString(methodArgs));
            System.out.println("start");
            final Object result = methodProxy.invokeSuper(object, methodArgs);
            System.out.println("end");
            return result;
        });
        user.greet();
        user.run(1.5D);
    }
}
