package org.example.basic.algorithm.sort;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-08-01 17:41
 */
public class MergeSort extends Sort {

    private int[] sort(int[] array) {
        sort0(array, 0, array.length - 1);
        return array;
    }

    private void sort0(int[] array, int start, int end) {
        if (start == end) {
            return;
        }
        int middle = start + ((end - start) >> 1);
        sort0(array, start, middle);
        sort0(array, middle + 1, end);
        merge(array, start, middle, end);
    }

    private void merge(int[] array, int start, int middle, int end) {
        int[] tempArray = new int[end - start + 1];
        int lIndex = start, rIndex = middle + 1, lEnd = middle, rEnd = end;
        int nIndex = 0;
        while (lIndex <= lEnd && rIndex <= rEnd) {
            if (array[lIndex] < array[rIndex]) {
                tempArray[nIndex++] = array[lIndex++];
            } else {
                tempArray[nIndex++] = array[rIndex++];
            }
        }
        if (lIndex <= lEnd) {
            while (lIndex <= lEnd) {
                tempArray[nIndex++] = array[lIndex++];
            }
        } else if (rIndex <= rEnd) {
            while (rIndex <= rEnd) {
                tempArray[nIndex++] = array[rIndex++];
            }
        }
        for (int i = 0; i < tempArray.length; i++) {
            array[start + i] = tempArray[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = {1,4,2,8,5,7};
        System.out.println(Arrays.toString(new MergeSort().sort(arr)));
    }
}
