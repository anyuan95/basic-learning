package org.example.basic.string;

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
}
