package org.example.basic.oj.leetcode.Q55;

/**
 * @author anyuan
 * @since 2021-09-14 22:48
 */
class Solution {
    /**
     * 贪心
     * <p>
     * 1 <= nums.length <= 3 * 10^4
     * 0 <= nums[i] <= 10^5
     *
     * @param nums
     * @return
     */
    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        final int n = nums.length;
        int maxIndex = nums[0];
        // 遍历只需要到最大能达到的位置就可以了，后面的位置肯定是走不到了
        for (int i = 1; i <= maxIndex; i++) {
            // 在每个点时找到当前这个点和前面的点能够到达的最远距离
            // 如果到达某个点时，发现从这个点已经能到达终点就返回true
            if (i + nums[i] >= n - 1) {
                return true;
            }
            maxIndex = Math.max(maxIndex, i + nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(solution.canJump(new int[]{3, 2, 1, 0, 4}));
        System.out.println(solution.canJump(new int[]{1,2}));
    }
}
