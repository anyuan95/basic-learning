package org.example.basic.java.pojo;

/**
 * @author anyuan
 * @since 2021-08-30 11:01
 */
public class TestUseFastAccessorMethods {

    public static void main(String[] args) {
        final User user = new User();
        user.setAge(26);
        user.setName("Eyal");
        System.out.println(user);
    }
}
