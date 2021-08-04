package org.example.basic.algorithm.merge;

import org.example.basic.helper.ArrayHelper;

/**
 * 逆序对
 * 问题描述：
 * 求一个数组中所有逆序对个数。
 *
 * @author anyuan
 * @since 2021-08-02 14:13
 */
public class ReversePair {

    /**
     * 暴力遍历，O(N^2)
     *
     * @param array
     * @return
     */
    private int reversePair_Force(int[] array) {
        int count = 0;
        for (int i = array.length - 1; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                if (array[i] < array[j]) {
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 归并方式
     * 左指针和右指针都从后向前遍历，当左指针的值大于右指针的值时，计数器增加右数组中右指针到右数组头位置的数量
     *
     * @param array
     * @return
     */
    private int reversePair(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        return divide(array, 0, array.length - 1);
    }

    private int divide(int[] array, int left, int right) {
        if (left == right) {
            return 0;
        }
        int middle = left + ((right - left) >> 1);
        return divide(array, left, middle)
                + divide(array, middle + 1, right)
                + merge(array, left, middle, right);
    }

    private int merge(int[] array, int left, int middle, int right) {
        int count = 0;
        int[] newArray = new int[right - left + 1];
        int lStart = left, lEnd = middle, rStart = middle + 1, rEnd = right, nIndex = newArray.length - 1;
        while (lEnd >= lStart && rEnd >= rStart) {
            // 两个指针相同时，先复制右边的
            count += array[lEnd] > array[rEnd] ? (rEnd - rStart + 1) : 0;
            newArray[nIndex--] = array[lEnd] > array[rEnd] ? array[lEnd--] : array[rEnd--];
        }
        while (lEnd >= lStart) {
            newArray[nIndex--] = array[lEnd--];
        }
        while (rEnd >= rStart) {
            newArray[nIndex--] = array[rEnd--];
        }
        for (int i = 0; i < newArray.length; i++) {
            array[left + i] = newArray[i];
        }
        return count;
    }

    public static void main(String[] args) {
        final ReversePair reversePair = new ReversePair();
        for (int i = 0; i < 100; i++) {
            int[] arr = ArrayHelper.generateRandomArray(10, 10);
            final int answer1 = reversePair.reversePair_Force(arr);
            final int answer2 = reversePair.reversePair(arr);
            if (answer1 != answer2) {
                System.out.println("error");
                return;
            }
        }
        System.out.println("ok");
    }

}
