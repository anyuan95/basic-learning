package org.example.foo.basic;

/**
 * @author anyuan
 * @since 2021-05-11 14:14
 */
public class TestPrivateInnerClass {

    User user;

    private class User {
        int name;

        public void doSth() {
            final TestPrivateInnerClass aClass = TestPrivateInnerClass.this;
            System.out.println(aClass);
        }

    }
}
