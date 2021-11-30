package org.example.basic.oj.leetcode.Q786;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * @author anyuan
 * @date 2021-11-29 20:50
 */
class Solution {

    /**
     * 暴力，大概率得超时
     * <p>
     * 2 <= arr.length <= 1000
     * 1 <= arr[i] <= 3 * 10^4
     * arr[0] == 1
     * arr[i] 是一个 素数 ，i > 0
     * arr 中的所有数字 互不相同 ，且按 严格递增 排序
     * 1 <= k <= arr.length * (arr.length - 1) / 2
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        final PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<>(
                (o1, o2) -> o1.getKey() * o2.getValue() - o2.getKey() * o1.getValue());
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                queue.add(new Pair<>(arr[i], arr[j]));
            }
        }
        int index = 1;
        while (index++ < k) {
            queue.poll();
        }
        final Pair<Integer, Integer> pair = queue.poll();
        return new int[]{pair.getKey(), pair.getValue()};
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.kthSmallestPrimeFraction(new int[]{1, 2, 3, 5}, 3)));
        System.out.println(Arrays.toString(solution.kthSmallestPrimeFraction(new int[]{1, 7}, 1)));
    }
}
