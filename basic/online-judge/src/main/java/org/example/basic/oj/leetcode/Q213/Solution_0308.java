package org.example.basic.oj.leetcode.Q213;

/**
 * @author anyuan
 * @date 2022-03-08 10:43
 */
public class Solution_0308 {
    /**
     * 与第一版的区别在于，第二版把数组的首尾连起来了
     * 相当于把问题拆成两部分，第一部分是[0,n-2]，第二部分是[1,n-1]
     *
     * @param nums
     * @return
     */
    public int _rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }

        final int n = nums.length;
        int[] dp1 = new int[n];
        dp1[0] = nums[0];
        dp1[1] = Math.max(dp1[0], nums[1]);
        for (int i = 2; i < n - 1; i++) {
            dp1[i] = Math.max(dp1[i - 1], dp1[i - 2] + nums[i]);
        }

        int[] dp2 = new int[n];
        dp2[1] = nums[1];
        dp2[2] = Math.max(dp2[1], nums[2]);
        for (int i = 3; i < n; i++) {
            dp2[i] = Math.max(dp2[i - 1], dp2[i - 2] + nums[i]);
        }

        return Math.max(dp1[n - 2], dp2[n - 1]);
    }

    /**
     * 空间优化
     *
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return Math.max(nums[0], nums[1]);
        }
        final int n = nums.length;
        return Math.max(process(nums, 0, n - 2), process(nums, 1, n - 1));
    }

    private int process(int[] arr, int startIndex, int endIndex) {
        int first = arr[startIndex], second = Math.max(first, arr[startIndex + 1]), temp;
        for (int i = startIndex + 2; i <= endIndex; i++) {
            temp = Math.max(first + arr[i], second);
            first = second;
            second = temp;
        }
        return second;
    }
}
