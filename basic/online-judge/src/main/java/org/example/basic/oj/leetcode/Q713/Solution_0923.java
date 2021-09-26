package org.example.basic.oj.leetcode.Q713;

/**
 * @author anyuan
 * @since 2021-09-23 11:07
 */
class Solution_0923 {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if (k == 0) {
            return 0;
        }

        int answer = 0;
        int currentProduct = 1;
        // 调整startIndex时间：1.找到小于k的值时设置初始值； 2.发现大于k
        int startIndex = -1;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < k) {
                if (startIndex == -1) {
                    startIndex = i;
                }

                currentProduct *= nums[i];
                // 调整startIndex位置，直到满足条件为止
                while (currentProduct >= k) {
                    currentProduct /= nums[startIndex++];
                }

                // 加上以自身结尾的满足条件的部分
                // 从i到j，以j结尾的集合公有j-i+1个
                answer += (i - startIndex + 1);
            } else {
                // 重置startIndex
                startIndex = -1;
                // 实时乘积也得重置
                currentProduct = 1;
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution_0923 solution = new Solution_0923();
//        System.out.println(solution.numSubarrayProductLessThanK(new int[]{10, 5, 2, 6}, 100));
        System.out.println(solution.numSubarrayProductLessThanK(new int[]{100, 2, 3, 4, 100, 5, 6, 7, 100}, 100));
    }
}
