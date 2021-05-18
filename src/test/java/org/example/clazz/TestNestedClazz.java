package org.example.clazz;

import org.junit.rules.TestName;

/**
 * @author anyuan
 * @since 2021-05-18 14:05
 */
public class TestNestedClazz {

    private String name;

    private static String TEST = "A";

    public static final class MemberNestedClass {
        // ...

        public void m1() {
            System.out.println(TEST);
        }
    }

    public class MemberNestedNonStaticClass {

    }

    public void localNestedClass() {
        final class LocalNestedClass {
            // ...
        }
    }

    public void anonymousNestedClass() {
        new Runnable() {
            @Override
            public void run() {
                // ...
            }
        };
    }

    private static class PrivateNestedClass {

    }

    public static void main(String[] args) {
        final TestNestedClazz clazz = new TestNestedClazz();
        final MemberNestedNonStaticClass memberNestedNonStaticClass = clazz.new MemberNestedNonStaticClass();

    }

}
