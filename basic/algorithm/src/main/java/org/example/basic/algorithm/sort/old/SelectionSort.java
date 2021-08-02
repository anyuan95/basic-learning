package org.example.basic.algorithm.sort.old;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-01-04 11:30
 */
public class SelectionSort {

    public static void main(String[] args) {
        int[] array = {4, 5, 3, 6, 2, 5, 1};
        System.out.println(Arrays.toString(sort(array)));
    }

    public static int[] sort(int[] array) {
        for (int i = 0; i < array.length; i++) {
            int min = array[i], minIndex = i, temp;
            for (int j = i; j < array.length; j++) {
                if (array[j] < min) {
                    min = array[j];
                    minIndex = j;
                }
            }
            temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }
}
