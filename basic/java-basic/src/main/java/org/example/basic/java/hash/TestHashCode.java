package org.example.basic.java.hash;

import lombok.Data;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

/**
 * @author anyuan
 * @since 2021-08-30 15:14
 */
public class TestHashCode {

    public static void main(String[] args) {
        final Random random = new Random();

//        Object o = 1;
//        System.out.println(o.hashCode());
//        System.out.println(Objects.hashCode(o));
//        User u1 = new User();
//        System.out.println(Objects.hashCode(u1) ^ Objects.hashCode(random.nextInt()));
//        User u2 = new User();
//        System.out.println(Objects.hashCode(u2) ^ Objects.hashCode(random.nextInt()));
//        System.out.println(Objects.hashCode(u1) ^ Objects.hashCode(u2));

//        System.out.println(new User().hashCode());

        final HashMap<User, User> map = new HashMap<>(Integer.MAX_VALUE);
        map.put(new User(1), new User(1));
        map.put(new User(2), new User(2));
        map.put(new User(3), new User(3));
        map.put(new User(4), new User(4));
        map.put(new User(5), new User(5));
        map.put(new User(6), new User(6));
        map.put(new User(7), new User(7));
        map.put(new User(8), new User(8));
        map.put(new User(9), new User(9));
        map.put(new User(10), new User(10));
        map.put(new User(11), new User(11));
        map.put(new User(12), new User(12));
        map.put(new User(13), new User(13));
        map.put(new User(14), new User(14));
        map.put(new User(15), new User(15));
        map.put(new User(16), new User(16));
        map.put(new User(17), new User(17));
        map.put(new User(18), new User(18));
        map.put(new User(19), new User(19));
        map.put(new User(20), new User(20));
        System.out.println(map);
    }
}

@ToString
@Getter
class User {
    private String name;
    private Integer age;

    public User() {
    }

    public User(Integer age) {
        this.age = age;
    }
}
