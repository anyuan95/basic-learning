package org.example.basic.oj.leetcode.Q334;

import java.util.PriorityQueue;

/**
 * 给你一个整数数组 nums ，判断这个数组中是否存在长度为 3 的递增子序列。
 * <p>
 * 如果存在这样的三元组下标 (i, j, k) 且满足 i < j < k ，使得 nums[i] < nums[j] < nums[k] ，返回 true ；否则，返回 false 。
 * <p>
 * 1 <= nums.length <= 5 * 105
 * -231 <= nums[i] <= 231 - 1
 * <p>
 * 进阶：你能实现时间复杂度为 O(n) ，空间复杂度为 O(1) 的解决方案吗？
 *
 * @author anyuan
 * @date 2022-01-12 11:25
 */
class Solution {
    /**
     * 这个三元组中，最小值永远取最小元素，中间值取比最小元素大的次小元素，然后只要发现比中间值大的数，就说明可以构成三元组
     *
     * @param nums
     * @return
     */
    public boolean increasingTriplet(int[] nums) {
        final int n = nums.length;
        int a = Integer.MAX_VALUE;
        int b = Integer.MAX_VALUE;

        for (int num : nums) {
            if (num <= a) {
                a = num;
            } else if (num <= b) {
                b = num;
            } else {
                return true;
            }
        }
        return false;
    }

    /**
     * 记录当前值左边的最大值（的下标，如果有相等的就选最靠左的），记录当前值右边的最大值（的下标，如果有相等的就选择最靠右的）
     * 当前值从左到右移动过程中，不断调整上边的两个值
     * <p>
     * 每个位置右边的最大值是一直在变的，
     *
     * @param nums
     * @return
     */
    public boolean _increasingTriplet(int[] nums) {
        final int n = nums.length;
        int leftMinValueIndex = 0, rightMaxValueIndex = n - 1;
        // 初始化lm和rm，实际上只要初始化rm即可。因为遍历要从0位置开始。
        final PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[1] == o2[1]) {
                return o2[0] - o1[0];
            }
            return o2[1] - o1[1];
        });
        for (int i = n - 1; i > 1; i--) {
            // 只有发现更大值时才需要更新
            if (nums[i] > nums[rightMaxValueIndex]) {
                rightMaxValueIndex = i;
            }
            pq.add(new int[]{i, nums[i]});
        }
        for (int i = 1; i < n - 1; i++) {
            int cur = nums[i];
            if (cur > nums[leftMinValueIndex] && cur < pq.peek()[1]) {
                return true;
            }
            // 如果当前值小于左边的最小值，那么当前值就变成最小值了
            leftMinValueIndex = cur < nums[leftMinValueIndex] ? i : leftMinValueIndex;
            // 如果当前下标就是右侧最大值下标，那么就弹出去
            // 如果右侧最大值下标小于当前下标，则说明这个值是在当前值的左边，无效，也需要弹出去
            while (i >= pq.peek()[0]) {
                pq.poll();
            }
        }
        return false;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.increasingTriplet(new int[]{1, 2, 3, 4, 5}));
        System.out.println(solution.increasingTriplet(new int[]{2, 1, 5, 0, 4, 6}));
        System.out.println(solution.increasingTriplet(new int[]{5, 4, 3, 2, 1}));
    }
}
