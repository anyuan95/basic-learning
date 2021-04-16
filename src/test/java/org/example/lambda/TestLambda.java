package org.example.lambda;

import cn.hutool.core.collection.ListUtil;
import com.google.common.collect.Lists;
import org.example.model.Gender;
import org.example.model.User;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author anyuan
 * @since 2021-02-22 10:29
 */
public class TestLambda {

    interface Swapper {
        void swap(int[] array, int startIndex, int endIndex);
    }

    private static void execute(Swapper swapper, int[] array, int startIndex, int endIndex) {
        swapper.swap(array, startIndex, endIndex);
    }

    public static void main(String[] args) {
        int[] myArray = {3, 1, 4, 5, 2};
        Swapper swapper = (array, startIndex, endIndex) -> {
            int temp = array[startIndex];
            array[startIndex] = array[endIndex];
            array[endIndex] = temp;
        };
        swapper.swap(myArray, 0, 4);
        System.out.println(Arrays.toString(myArray));
    }

    @Test
    public void testToMap() {
        final ArrayList<User> users = ListUtil.toList(
                User.builder().id(1L).firstName("a").build(),
                User.builder().id(2L).firstName("b").build(),
                User.builder().id(2L).firstName("c").build()
        );
        System.out.println(users.stream().collect(Collectors.toMap(User::getId, User::getFirstName, (u1, u2) -> u1)));
        System.out.println(users.stream().collect(Collectors.toMap(User::getId, User::getFirstName, (u1, u2) -> u2)));

        System.out.println(users.stream().collect(Collectors.toMap(User::getId, User::getFirstName, (u1, u2) -> u2, HashMap::new)).getClass());
        System.out.println(users.stream().collect(Collectors.toMap(User::getId, User::getFirstName, (u1, u2) -> u2, LinkedHashMap::new)).getClass());

        // 参数1-supplier：用于生成使用的集合；参数2-accumulator：用于将流中的每个值添加到集合中；参数3-combiner：用于合并多个流（当前测试仅用于并行流情况下）
        final HashMap<Object, Object> collect = users.stream().collect(HashMap::new, (map, user) -> map.put(user.getId(), user.getFirstName()), HashMap::putAll);
        System.out.println(collect);
        final HashMap<Object, Object> collectParallel = users.parallelStream().collect(HashMap::new, (map, user) -> map.put(user.getId(), user.getFirstName()), HashMap::putAll);
        System.out.println(collectParallel);
    }

    @Test
    public void testStream() {
        final List<User> users = Lists.newArrayList(
                User.builder().id(1L).firstName("John").lastName("Smith").gender(Gender.MALE).build(),
                User.builder().id(2L).firstName("Jane").lastName("Smith").gender(Gender.FEMALE).build(),
                User.builder().id(3L).firstName("Hawking").lastName("Stephen").gender(Gender.MALE).build(),
                User.builder().id(4L).firstName("Tony").lastName("Stark").gender(Gender.MALE).build(),
                User.builder().id(5L).firstName("Rose").lastName("Stephen").gender(Gender.OTHER).build(),
                User.builder().id(5L).firstName("Arthur").lastName("Pandragon").gender(Gender.FEMALE).build()
        );

        // distinct last-name
        System.out.println(users.stream().map(User::getLastName).distinct().collect(Collectors.toList()));
        // skip first 2 users
        System.out.println(users.stream().skip(2L).collect(Collectors.toList()));

    }

}
