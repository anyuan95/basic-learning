package org.example.basic.algorithm.sort;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-03-05 14:16
 */
public class QuickSort extends Sort {

    public static void main(String[] args) {
        int[] array = {4, 5, 3, 6, 2, 5, 1};
        System.out.println(Arrays.toString(sort(array)));
    }

    private static int[] sort(int[] array) {
        sort0(array, 0, array.length - 1);
        return array;
    }

    private static void sort0(int[] array, int start, int end) {
        if (start >= end) {
            return;
        }
        int partitionIndex = partition(array, start, end);
        sort0(array, start, partitionIndex - 1);
        sort0(array, partitionIndex + 1, end);
    }

    private static int partition(int[] array, int start, int end) {
        int pivot = array[end];
        int i = start, j = start;
        for (; i < end; i++) {
            if (array[i] < pivot) {
                swap(array, i, j);
                j++;
            }
        }
        swap(array, j, end);
        return j;
    }

}
