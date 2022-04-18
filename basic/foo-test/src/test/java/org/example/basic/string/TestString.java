package org.example.basic.string;

import org.junit.Test;

/**
 * @author anyuan
 * @since 2021-05-21 12:15
 */
public class TestString {

    public static void main(String[] args) {
        String str = "helloworld";
        final String reserved = str.chars().mapToObj(value -> (char) value).reduce("", (left, right) -> right + left, (s1, s2) -> s2 + s1);
        System.out.println(reserved);
        System.out.println(new StringBuffer(str).reverse());
    }

    @Test
    public void testEquals1() {
        final String s2 = new StringBuilder().append("StringTest").toString();
        System.out.println(s2.intern() == s2);
        final String s1 = new StringBuilder().append("String").append("Test").toString();
        System.out.println(s1.intern() == s1);
    }

    @Test
    public void testEquals2() {
        final String s1 = new StringBuilder().append("String").append("Test").toString();
        System.out.println(s1.intern() == s1);
        final String s2 = new StringBuilder().append("StringTest").toString();
        System.out.println(s2.intern() == s2);
    }

    @Test
    public void testDisOrder() {
        char[] chars2 = {'a', 'b', 'c'};
        char[] chars6 = {'m', 'n', 'o'};
        char[] chars8 = {'t', 'u', 'v'};
        char[] chars9 = {'w', 'x', 'y', 'z'};
        // 269826
        for (char c1 : chars2) {
            for (char c2 : chars6) {
                for (char c3 : chars9) {
                    for (char c4 : chars8) {
                        for (char c5 : chars2) {
                            for (char c6 : chars6) {
                                System.out.println("" + c1 + c2 + c3 + c4 + c5 + c6);
                            }
                        }
                    }
                }
            }
        }
    }
}
