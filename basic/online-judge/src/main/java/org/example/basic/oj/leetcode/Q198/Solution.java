package org.example.basic.oj.leetcode.Q198;

/**
 * @author anyuan
 * @since 2021-08-24 18:08
 */
class Solution {
    public int _rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        // 实际上只有两种决策：
        // 1.上一次偷完n位置了，这次偷N+2；
        // 2.上一次偷完n位置了，这次偷N+3；
        // 不会有n+1因为会触发警报；不会有n+4因为既然偷了n+4干嘛不偷n+2呢？反正没有负价值，带上n+2一定比不带总价值更高
        // 所以dp[i] = Math.max( dp[i-1], dp[i-2] + nums[i] )   (i>1)
        // 即：到第i位置时，最大价值就是，i-1的最大价值，和i-2最大价值+当前价值 中的最大值

        // dp[i] 表示到第i位置结束的话，能获取的最大收益
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(dp[0], nums[1]);
        for (int i = 2; i < nums.length; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + nums[i]);
        }
        return dp[nums.length - 1];
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
        }
        int first = nums[0], second = Math.max(first, nums[1]);
        for (int i = 2; i < nums.length; i++) {
            int temp = Math.max(first + nums[i], second);
            first = second;
            second = temp;
        }
        return second;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.rob(new int[]{2, 7, 9, 3, 1}));
        System.out.println(solution.rob(new int[]{1}));
        System.out.println(solution.rob(new int[]{1, 2}));
    }
}
