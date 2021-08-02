package org.example.basic.algorithm.recursive;

import javax.tools.JavaFileObject;

/**
 * @author anyuan
 * @since 2021-08-01 15:46
 */
public class FindMaxValueInArray {

    private static int findMaxValueInArray(int[] array) {
        return findMax(array, 0, array[0]);
    }

    private static int findMax(int[] array, int currentIndex, int currentMax) {
        if (currentIndex == array.length - 1) {
            return currentMax;
        }
        return findMax(array, currentIndex + 1, Math.max(array[currentIndex], currentMax));
    }

    /*-----*/

    /**
     * 二分法
     *
     * @return
     */
    private static int findMaxValueInArrayUsingDichotomy(int[] array) {
        return findMaxDichotomy(array, 0, array.length - 1);
    }

    private static int findMaxDichotomy(int[] array, int start, int end) {
        int middle = start + ((end - start) >> 1);
        // 两种方式，一种是直接将diff==1也作为结束条件（其实没必要），另一种是仅当s==e才结束
//        if (end - start == 1) {
//            System.out.println("diff = 1");
//            return Math.max(array[start], array[end]);
//        } else
        if (start == end) {
            System.out.println("same");
            return array[start];
        }
        return Math.max(findMaxDichotomy(array, start, middle), findMaxDichotomy(array, middle + 1, end));
    }

    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 8, 5, 7, 0, 9, -1};
//        System.out.println(findMaxValueInArray(arr));
        System.out.println(findMaxValueInArrayUsingDichotomy(arr));
    }
}
