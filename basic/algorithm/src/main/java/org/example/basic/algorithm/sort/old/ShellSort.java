package org.example.basic.algorithm.sort.old;

import java.util.Arrays;

/**
 * 希尔排序，指定不断减半的间隔gap，对每隔gap的一组数进行插入排序，直到gap为1为止
 *
 * @author anyuan
 * @since 2021-01-04 14:59
 */
public class ShellSort {

    public static void main(String[] args) {
        int[] array = {4, 5, 3, 6, 2, 5, 1};
        System.out.println(Arrays.toString(sort(array)));
    }

    public static int[] sort1(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length && i < gap * 2; i++) {
                for (int j = i; j < array.length; j += gap) {
                    if (array[j] < array[j - gap]) {
                        for (int k = i - gap; k < array.length; k += gap) {
                            if (array[j] < array[k]) {
                                int temp = array[j];
                                for (int l = j; l > k; l -= gap) {
                                    array[l] = array[l - gap];
                                }
                                array[k] = temp;
                            }
                        }
                    }
                }
            }
        }
        return array;
    }

    public static int[] sort2(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                if (array[i] < array[i - gap]) {
//                    for (int k = i % gap; k < i; k += gap) {
//                        if (array[k] > array[i]) {
                    int temp = array[i], j;
                    for (j = i; j >= gap; j -= gap) {
                        array[j] = array[j - gap];
                    }
                    array[j] = temp;
//                        }
//                    }
                }
            }
        }
        return array;
    }

    public static int[] sort(int[] array) {
        for (int gap = array.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < array.length; i++) {
                int j, temp = array[i];
                for (j = i - gap; j >= 0; j -= gap) {
                    if (array[j] > temp) {
                        array[j + gap] = array[j];
                    } else {
                        break;
                    }
                }
                array[j+gap] = temp;
            }
        }
        return array;
    }
}
