package org.example.basic.oj.leetcode.Q969;

import java.util.ArrayList;
import java.util.List;

class Solution {
    /**
     * 每2次操作都将当前无需区间内的最大值移动到它应该在的位置
     *
     * @param arr
     * @return
     */
    public List<Integer> pancakeSort(int[] arr) {
        final int n = arr.length;
        final List<Integer> answer = new ArrayList<>();
        for (int i = n - 1; i > 0; i--) {
            final int maxIndex = findMaxIndex(arr, i);
            // 如果最大值就是当前区间中最后一个位置，那就不用调整了
            if (maxIndex == i) {
                continue;
            }
            // 把最大值翻转到0位置
            if (maxIndex != 0) {
                reverseArray(arr, 0, maxIndex);
                answer.add(maxIndex + 1);
            }
            // 把最大值翻转到区间最后位置（i位置）
            reverseArray(arr, 0, i);
            answer.add(i + 1);
        }
        return answer;
    }

    // [s,e]
    public void reverseArray(int[] arr, int startIndex, int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            int temp = arr[startIndex];
            arr[startIndex] = arr[endIndex];
            arr[endIndex] = temp;

            startIndex++;
            endIndex--;
        }
    }

    private int findMaxIndex(int[] arr, int endIndex) {
        int maxIndex = 0;
        for (int i = 1; i <= endIndex; i++) {
            if (arr[maxIndex] < arr[i]) {
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        System.out.println(solution.pancakeSort(new int[]{3, 2, 4, 1}));
        System.out.println(solution.pancakeSort(new int[]{1,2,3}));
    }
}
