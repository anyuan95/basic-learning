package org.example.basic.oj.leetcode.Q786;

import javafx.util.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author anyuan
 * @date 2021-11-29 20:50
 */
class Solution {

    /**
     * 暴力，大概率得超时  --然而并没有
     * <p>
     * 实际上可以优化一下空间，做成大顶堆（只存k个数据），然后发现有比大顶堆小的数据，就弹出堆顶，把新数据放入就行了
     *
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

    /**
     * 大顶堆，空间优化
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] kthSmallestPrimeFraction_betterMemory(int[] arr, int k) {
        final Comparator<Pair<Integer, Integer>> comparator = (o1, o2) -> o2.getKey() * o1.getValue() - o1.getKey() * o2.getValue();
        final PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<>(comparator);
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                final Pair<Integer, Integer> pair = new Pair<>(arr[i], arr[j]);
                if (queue.size() < k) {
                    queue.add(pair);
                } else {
                    final Pair<Integer, Integer> peek = queue.peek();
                    // 发现比堆顶元素更小的值了，弹出堆顶，把新值放进去
                    if (comparator.compare(peek, pair) < 0) {
                        queue.poll();
                        queue.add(pair);
                    }
                }
            }
        }
        final Pair<Integer, Integer> poll = queue.poll();
        return new int[]{poll.getKey(), poll.getValue()};
    }

    /**
     * 多路归并思想
     *
     * @param arr
     * @param k
     * @return
     */
    public int[] kthSmallestPrimeFraction_multiwayMerge(int[] arr, int k) {
        // 注意，这种方式里Pair存的是下标，但比较的时候需要把下标对应的值取出来
        final PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<>(
                (o1, o2) -> arr[o1.getKey()] * arr[o2.getValue()] - arr[o2.getKey()] * arr[o1.getValue()]);
        final int n = arr.length;
        // n个数，会有n-1个数列，每个数列最小的数就是arr[0]/arr[i]
        // 每个数列中的数都一定是递增的
        for (int i = 1; i < n; i++) {
            queue.add(new Pair<>(0, i));
        }
        while (k-- > 1) {
            final Pair<Integer, Integer> poll = queue.poll();
            final Integer i = poll.getKey(), j = poll.getValue();
            // 这里需要是小于j，否则就不是真分数了
            if (i + 1 < j) {
                queue.add(new Pair<>(i + 1, j));
            }
        }
        final Pair<Integer, Integer> poll = queue.poll();
        return new int[]{arr[poll.getKey()], arr[poll.getValue()]};
    }

    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        final Solution solution = new Solution();
        System.out.println(Arrays.toString(solution.kthSmallestPrimeFraction_betterMemory(new int[]{1, 2, 3, 5}, 3)));
        System.out.println(Arrays.toString(solution.kthSmallestPrimeFraction_betterMemory(new int[]{1, 7}, 1)));
    }
}
