package org.example.basic.oj.leetcode.Q373;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.IntStream;

/**
 * @author anyuan
 * @date 2022-01-14 18:24
 */
class Solution {
    public List<List<Integer>> _kSmallestPairs(int[] nums1, int[] nums2, int k) {
        final PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (o2[0] + o2[1]) - (o1[0] + o1[1]));
        int currentMax = Integer.MIN_VALUE;
        for (int i = 0; i < Math.min(nums1.length, k); i++) {
            for (int j = 0; j < Math.min(nums2.length, k); j++) {
                final int sum = nums1[i] + nums2[j];
                if (pq.size() > k && sum >= currentMax) {
                    // 如果当前想要新插入的数比pq里最大的数更大或相等，则跳出当前循环。因为a不变，b里面后边的数一定更大，和一定就更大
                    break;
                }
                currentMax = Math.max(currentMax, sum);
                pq.add(new int[]{nums1[i], nums2[j]});
                if (pq.size() > k) {
                    pq.poll();
                }
            }
        }
        final List<List<Integer>> answer = new ArrayList<>();
        final int size = Math.min(pq.size(), k);
        for (int i = 0; i < size; i++) {
            final int[] poll = pq.poll();
            answer.add(0, Arrays.asList(poll[0], poll[1]));
        }
        return answer;
    }

    public List<List<Integer>> ___kSmallestPairs(int[] nums1, int[] nums2, int k) {
        PriorityQueue<List<Integer>> heap = new PriorityQueue<>((x, y) -> {
            return x.get(0) + x.get(1) - y.get(0) - y.get(1);
        });
        for (int i = 0, n = Math.min(k, nums1.length); i < n; i++) {
            for (int j = 0, m = Math.min(k, nums2.length); j < m; j++) {
                heap.add(Arrays.asList(nums1[i], nums2[j]));
            }
        }
        List<List<Integer>> res = new ArrayList<>();
        for (int i = 0; i < k && !heap.isEmpty(); i++) {
            res.add(heap.poll());
        }
        return res;
    }

    private boolean flag = true;

    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        final int n = nums1.length, m = nums2.length;
        if (m < n && !(flag = false)) {
            return kSmallestPairs(nums2, nums1, k);
        }
        final PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> (nums1[o1[0]] + nums2[o1[1]]) - (nums1[o2[0]] + nums2[o2[1]]));
        final List<List<Integer>> answer = new ArrayList<>();
        for (int i = 0; i < Math.min(n, k); i++) {
            pq.add(new int[]{i, 0});
        }
        while (answer.size() < k && !pq.isEmpty()) {
            final int[] poll = pq.poll();
            int a = poll[0], b = poll[1];
            answer.add(flag ? Arrays.asList(nums1[a], nums2[b]) : Arrays.asList(nums2[b], nums1[a]));
            if (b + 1 < m) {
                pq.add(new int[]{a, b + 1});
            }
        }
        return answer;
    }

//    boolean flag = true;

    public List<List<Integer>> __kSmallestPairs(int[] nums1, int[] nums2, int k) {
        int n = nums1.length, m = nums2.length;
        if (n > m && !(flag = false)) return kSmallestPairs(nums2, nums1, k);
        List<List<Integer>> ans = new ArrayList<>();
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> (nums1[a[0]] + nums2[a[1]]) - (nums1[b[0]] + nums2[b[1]]));
        for (int i = 0; i < Math.min(n, k); i++) q.add(new int[]{i, 0});
        while (ans.size() < k && !q.isEmpty()) {
            int[] poll = q.poll();
            int a = poll[0], b = poll[1];
            ans.add(new ArrayList() {{
                add(flag ? nums1[a] : nums2[b]);
                add(flag ? nums2[b] : nums1[a]);
            }});
                if (b + 1 < m) q.add(new int[]{a, b + 1});
        }
        return ans;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        final int[] nums1 = IntStream.range(1, 10000).toArray();
        final int[] nums2 = IntStream.range(1, 10000).toArray();
        System.out.println(solution.kSmallestPairs(nums1, nums2, 1000));
//        System.out.println(solution.kSmallestPairs(new int[]{1, 7, 11}, new int[]{2, 4, 6}, 3));
//        System.out.println(solution.kSmallestPairs(new int[]{1, 1, 2}, new int[]{1, 2, 3}, 2));
//        System.out.println(solution.kSmallestPairs(new int[]{1, 2}, new int[]{3}, 3));
    }
}
