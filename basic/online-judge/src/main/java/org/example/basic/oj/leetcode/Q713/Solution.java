package org.example.basic.oj.leetcode.Q713;

/**
 * 求乘积小于k的连续子数组个数
 * 1 <= nums.length <= 3 * 104
 * 1 <= nums[i] <= 1000
 * 0 <= k <= 106
 *
 * @author anyuan
 * @since 2021-09-04 18:56
 */
class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) {
            return 0;
        }
        int answer = 0, currentMulti = 1, multiStartIndex = 0, n = nums.length;
        for (int i = 0; i < n; i++) {
            currentMulti *= nums[i];
            if (currentMulti < k) {
                // 如果乘以当前数还满足小于k
                // 每次统计以i结束的满足条件的子数组个数
                // 若从i~j的乘积都满足条件，则共有j-i+1个以i结尾的子数组
                answer += i - multiStartIndex + 1;

            } else {
                // 如果乘以当前数不满足小于k了
                // 则就把multiStart向右移动，直到满足为止，或者直到撞到i为止
                while (multiStartIndex < i && currentMulti >= k) {
                    currentMulti = currentMulti / nums[multiStartIndex++];
                }
                // 也有可能，直到指针相撞了，还是没小于，那就不加
                if (currentMulti < k) {
                    answer += i - multiStartIndex + 1;
                } else {
                    // 如果已经相撞了，还没小于，说明这段的值都大于等于k
                    currentMulti = 1;
                    multiStartIndex++;
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        System.out.println(solution.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6, 7}, 100));
        System.out.println(solution.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6, 7}, 3));
    }
}
