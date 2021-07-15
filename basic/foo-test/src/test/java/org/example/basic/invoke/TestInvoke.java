package org.example.basic.invoke;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author anyuan
 * @since 2021-06-07 16:10
 */
public class TestInvoke {
    public static void main(String[] args) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        final Class<TestDL> targetClass = TestDL.class;
//        final TestDL t2 = targetClass.newInstance();
//        System.out.println(t2 == TestDL.ins());
        final Constructor<TestDL> constructor = targetClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        final TestDL t1 = constructor.newInstance();
        System.out.println(t1 == TestDL.ins());
    }
}
