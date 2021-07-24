package org.example.basic.java.feature8;

import org.example.basic.java.feature8.model.User;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;

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
    public void testOverload() {
        final Function<String, User> strFunc = User::new;
        final Function<Long, User> longFunc = User::new;
    }

}
