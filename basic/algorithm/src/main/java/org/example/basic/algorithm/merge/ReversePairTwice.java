package org.example.basic.algorithm.merge;

import org.example.basic.helper.ArrayHelper;

/**
 * 逆序对变体
 * 仅当左数>右数*2时，才进行统计
 *
 * @author anyuan
 * @since 2021-08-02 15:07
 */
public class ReversePairTwice {

    private int reversePairTwice_Force(int[] array) {
        if (array == null || array.length < 2) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j] * 2) {
                    count++;
                }
            }
        }
        return count;
    }

    private int reversePairTwice(int[] array) {
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
        // 左右数组都是有序的
        // 先进行计算，计算完成后再进行合并
        int lStart = left, lEnd = middle, rStart = middle + 1, rEnd = right;
        int lPointer = lEnd, rPointer = rEnd;
        while (lPointer >= lStart) {
            while (rPointer >= rStart) {
                if (array[lPointer] > array[rPointer] << 1) {
                    count += rPointer - rStart + 1;
                    break;
                }
                rPointer--;
            }
            lPointer--;
        }

        int[] newArray = new int[right - left + 1];
        int nIndex = newArray.length - 1;
        while (lEnd >= lStart && rEnd >= rStart) {
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
        final ReversePairTwice reversePairTwice = new ReversePairTwice();
//        int[] arr = {5, 7, 1, 4, 3};
//        System.out.println(reversePairTwice.reversePairTwice_Force(arr));
//        System.out.println(reversePairTwice.reversePairTwice(arr));
//
        for (int i = 0; i < 100; i++) {
            int[] arr = ArrayHelper.generateRandomArray(10, 10);
            final int answer1 = reversePairTwice.reversePairTwice_Force(arr);
            final int answer2 = reversePairTwice.reversePairTwice(arr);
            if (answer1 != answer2) {
                System.out.println("error");
                return;
            }
        }
        System.out.println("ok");
    }

}
