package org.example.sort;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-01-18 11:28
 */
public class MergeSort extends Sort {

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
        final int middleIndex = (start + end) / 2;
        sort0(array, start, middleIndex);
        sort0(array, middleIndex + 1, end);
        merge(array, start, end);
    }

    private static void merge(int[] array, int start, int end) {
        int i = start, j = (start + end) / 2, p = (start + end) / 2 + 1, q = end;
        System.out.println("i=" + i + ",j=" + j + ",p=" + p + ",q=" + q + ",array=" + Arrays.toString(array));

        int k = 0;
        final int[] temp = new int[end - start + 1];

        while (i <= j && p <= q) {
            if (array[i] <= array[p]) {
                temp[k++] = array[i++];
            } else {
                temp[k++] = array[p++];
            }
        }
        /* !!! for(;=;) */
        if (i <= j) {
            for (int l = i; l <= j; l++) {
                temp[k++] = array[l];
            }
        } else if (p <= q) {
            for (int l = p; l <= q; l++) {
                temp[k++] = array[l];
            }
        }
        for (int l = 0; l < k; l++) {
            array[start++] = temp[l];
        }
    }

}
