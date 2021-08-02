package org.example.basic.algorithm.sort;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-08-01 17:23
 */
public class MergeSort_Wrong extends Sort {

    private int[] sort(int[] array) {
        return sort0(array, 0, array.length - 1);
    }

    private int[] sort0(int[] array, int start, int end) {
        if (start == end) {
            return new int[]{start};
        }
        int middle = start + ((end - start) >> 1);
        return merge(sort0(array, start, middle), sort0(array, middle + 1, end));
    }

    private int[] merge(int[] leftArray, int[] rightArray) {
        final int[] newArray = new int[leftArray.length + rightArray.length];
        int lIndex = 0, rIndex = 0, nIndex = 0;
        while (lIndex < leftArray.length && rIndex < rightArray.length) {
            if (leftArray[lIndex] < rightArray[rIndex]) {
                newArray[nIndex] = leftArray[lIndex];
                lIndex++;
            } else {
                newArray[nIndex] = rightArray[rIndex];
                rIndex++;
            }
            nIndex++;
        }
        if (lIndex < leftArray.length - 1) {
            while (lIndex < leftArray.length) {
                newArray[nIndex] = leftArray[lIndex];
                lIndex++;
                nIndex++;
            }
        } else if (rIndex < rightArray.length - 1) {
            while (rIndex < rightArray.length) {
                newArray[nIndex] = rightArray[rIndex];
                rIndex++;
                nIndex++;
            }
        }
        return newArray;
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 8, 5, 7};
        System.out.println(Arrays.toString(new MergeSort_Wrong().sort(arr)));
    }
}
