package org.example.basic.oj.leetcode.Q209;

/**
 * 长度最小的子数组
 * <p>
 * 给定数组nums和目标值target
 * 找出和满足>=target的长度最小的连续子数组 的长度
 * <p>
 * 1 <= target <= 109
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 105
 * <p>
 * 要求：
 * 1.时间复杂度O(n)
 * 2.时间复杂度O(NlogN)
 *
 * @author anyuan
 * @since 2021-09-05 14:34
 */
class Solution {
    /**
     * 最小有效结果是1
     * 如果nums总和打不到target，则返回0
     *
     * @param target
     * @param nums
     * @return
     */
    public int minSubArrayLen(int target, int[] nums) {
        int answer = Integer.MAX_VALUE, n = nums.length;
        int currentSum = 0, sumStartIndex = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] >= target) {
                // 最小有效结果就是1
                return 1;
            }
            currentSum += nums[i];
            // 这部分的if可以去掉，因为实际上处理的内容和while直接处理的结果是一样的
//            if (currentSum >= target) {
//                answer = Math.min(answer, i - sumStartIndex + 1);
                // 从前面开始逐个减掉元素，直到currentSum刚刚好小于target
                // sumStartIndex == i的情况在进入for时候已经判断过了
                while (sumStartIndex < i && currentSum >= target) {
                    // 有可能出现减掉sumStartIndex后，依然满足>=target的情况，这时候应该更新answer
                    answer = Math.min(answer, i - sumStartIndex + 1);
                    currentSum -= nums[sumStartIndex++];
                }
                // 结束循环有两种情况：1.sumStartIndex撞到i了；2.currentSum小于target了
//            }
        }
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3}));
        System.out.println(solution.minSubArrayLen(7, new int[]{2, 3, 1, 2, 4, 3, 8}));
        System.out.println(solution.minSubArrayLen(6, new int[]{2, 2, 2, 2, 2, 2}));
        System.out.println(solution.minSubArrayLen(60, new int[]{2, 2, 2, 2, 2, 2}));
    }
}
