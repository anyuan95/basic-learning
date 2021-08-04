package org.example.basic.algorithm.sort;

import java.util.Arrays;

/**
 * @author anyuan
 * @since 2021-08-01 17:41
 */
public class MergeSort extends Sort {

    protected void sort(int[] array) {
//        sort0(array, 0, array.length - 1);
        sort1(array);
    }

    /**
     * 递归方式
     *
     * @param array
     * @param start
     * @param end
     */
    private void sort0(int[] array, int start, int end) {
        if (start == end) {
            return;
        }
        int middle = start + ((end - start) >> 1);
        sort0(array, start, middle);
        sort0(array, middle + 1, end);
        merge(array, start, middle, end);
    }

    /**
     * 迭代方式
     */
    private void sort1(int[] array) {
        int step = 1;
        while (step < array.length) {
            int left = 0, right = 0, middle = 0;
            while (left < array.length) {
                middle = left + step - 1;
                if (middle >= array.length) {
                    break;
                }
                right = Math.min(middle + step, array.length - 1);
                merge(array, left, middle, right);
                left = right + 1;
            }
            // 此处先进行一次判断，避免直接*=2导致数据溢出
            if (step > (array.length >>> 1)) {
                break;
            }
            step <<= 1;
        }
    }

    private void merge(int[] array, int start, int middle, int end) {
        int[] tempArray = new int[end - start + 1];
        int lIndex = start, rIndex = middle + 1, lEnd = middle, rEnd = end;
        int nIndex = 0;
        while (lIndex <= lEnd && rIndex <= rEnd) {
            // 注意，这里使用<和<=时，会对稳定性产生影响
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
        int[] arr = {7, 5, 8, 2, 4, 1};
        new MergeSort().sort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
