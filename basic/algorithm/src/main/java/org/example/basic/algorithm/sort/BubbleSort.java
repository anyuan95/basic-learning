package org.example.basic.algorithm.sort;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2020-12-31 11:19
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] array = {4, 5, 3, 6, 2, 5, 1};
        System.out.println(Arrays.toString(sort(array)));
    }

    public static int[] sort(int[] array) {
        int temp;
        for (int i = 0; i < array.length; i++) {
            boolean doSwap = false;
            for (int j = 0; j < array.length - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                    doSwap = true;
                }
            }
            if (!doSwap)
                return array;
        }
        return array;
    }

}
