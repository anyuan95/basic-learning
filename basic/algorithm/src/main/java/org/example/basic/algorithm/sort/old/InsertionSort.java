package org.example.basic.algorithm.sort.old;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2020-12-31 15:21
 */
public class InsertionSort {

    public static void main(String[] args) {
        int[] array = {4, 5, 3, 6, 2, 5, 1};
        System.out.println(Arrays.toString(sort(array)));
    }

    public static int[] sort1(int[] array) {
        for (int i = 1; i < array.length; i++) {
            if (array[i] < array[i - 1]) {
                for (int j = 0; j < i; j++) {
                    if (array[i] < array[j]) {
                        /*从j至i向后移动*/
                        int temp = array[i];
                        for (int k = i; k > j; k--) {
                            array[k] = array[k - 1];
                        }
                        array[j] = temp;
                        break;
                    }
                }
            }
        }
        return array;
    }

    public static int[] sort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int j, temp = array[i];
            for (j = i - 1; j >= 0; j--) {
                if (array[j] > temp) {
                    array[j + 1] = array[j];
                } else {
                    break;
                }
            }
            array[j + 1] = temp;
        }
        return array;
    }
}
