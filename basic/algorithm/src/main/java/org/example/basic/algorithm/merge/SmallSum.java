package org.example.basic.algorithm.merge;

import org.example.basic.helper.ArrayHelper;

import java.util.Arrays;

/**
 * 小和问题
 * 问题描述：给定一个数组，对于数组中的每个数x，计算其左侧所有比x小的数的和。将所有的到的数加到一起，作为问题的返回结果。
 *
 * @author anyuan
 * @since 2021-08-02 11:05
 */
public class SmallSum {

    /**
     * 思路：
     * 使用归并排序方式。
     * 对于数组中每个数a，在其右侧有n个数比a大，则有：将所有的n*a求和，得到的结果即为问题的最终结果。
     *
     * @param array
     * @return
     */
    private int smallSum(int[] array) {
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
        return divide(array, left, middle) + divide(array, middle + 1, right) + merge(array, left, middle, right);
    }

    private int merge(int[] array, int left, int middle, int right) {
        int answer = 0;
        final int[] newArray = new int[right - left + 1];
        int nIndex = 0, lIndex = left, lEnd = middle, rIndex = middle + 1, rEnd = right;
        while (lIndex <= lEnd && rIndex <= rEnd) {
            // 这里需要注意，左指针和右指针指向的数相同时，需要先将右指针的值放入临时数组，然后右指针右移
            // 因为只有移动右指针，直到找到一个大于左指针的值后，才能确定右数组中有多少个大于左指针当前值的元素
            answer += array[lIndex] < array[rIndex] ? array[lIndex] * (rEnd - rIndex + 1) : 0;
            newArray[nIndex++] = array[lIndex] < array[rIndex] ? array[lIndex++] : array[rIndex++];
        }
        while (lIndex <= lEnd) {
            newArray[nIndex++] = array[lIndex++];
        }
        while (rIndex <= rEnd) {
            newArray[nIndex++] = array[rIndex++];
        }
        for (int index = 0; index < newArray.length; index++) {
            array[index + left] = newArray[index];
        }
        return answer;
    }

    private void merge0(int[] array, int left, int middle, int right) {
        final int[] newArray = new int[right - left + 1];
        int nIndex = 0, lIndex = left, lEnd = middle, rIndex = middle + 1, rEnd = right;
        while (lIndex <= lEnd && rIndex <= rEnd) {
            newArray[nIndex++] = array[lIndex] < array[rIndex] ? array[lIndex++] : array[rIndex++];
        }
        while (lIndex <= lEnd) {
            newArray[nIndex++] = array[lIndex++];
        }
        while (rIndex <= rEnd) {
            newArray[nIndex++] = array[rIndex++];
        }
        for (int index = 0; index < newArray.length; index++) {
            array[index + left] = newArray[index];
        }
    }

    private int smallSum_Force(int[] array) {
        int answer = 0;
        if (array == null || array.length < 2) {
            return 0;
        }
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < i; j++) {
                answer += array[j] < array[i] ? array[j] : 0;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            int[] arr = ArrayHelper.generateRandomArray(5, 10);
            final SmallSum smallSum = new SmallSum();
            final int ans1 = smallSum.smallSum_Force(arr);
            final int ans2 = smallSum.smallSum(arr);
            if (ans1 != ans2) {
                System.out.println("error!");
                System.out.println(Arrays.toString(arr));
            }
        }
        System.out.println("ok");
    }

}
