package org.example.lambda;

import java.util.Arrays;
import java.util.function.IntBinaryOperator;

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
}
