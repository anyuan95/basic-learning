package org.example.basic.oj.leetcode.Q581;

public class Solution {

    public int findUnsortedSubarray(int[] nums) {
        // 找到满足以下条件的left位置：
        // left左侧所有元素小于left右侧
        // left左侧所有元素都是升序的

        final int length = nums.length;

        int i = 0, j = length - 1;
        while (i < j && nums[i] <= nums[i + 1]) {
            i++;
        }
        while (i < j && nums[j - 1] <= nums[j]) {
            j--;
        }
        int l = i, r = j;
        int min = nums[i], max = nums[j];
        // 先找到范围内的最大最小值
        for (int u = l; u <= r; u++) {
            min = Math.min(min, nums[u]);
            max = Math.max(max, nums[u]);
        }
        // 然后从l向左找小于min的，从r向右找大于max的
        while (l >= 0 && nums[l] > min) {
            l--;
        }
        while (r < length && nums[r] < max) {
            r++;
        }
        return l == r ? 0 : (r - 1) - (l + 1) + 1;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().findUnsortedSubarray(new int[]{1}));
        System.out.println(new Solution().findUnsortedSubarray(new int[]{2, 3, 1, 4}));

        System.out.println(new Solution().findUnsortedSubarray(new int[]{2, 1}));
        System.out.println(new Solution().findUnsortedSubarray(new int[]{2, 6, 4, 8, 10, 9, 15}));
        System.out.println(new Solution().findUnsortedSubarray(new int[]{1, 2, 3, 4}));

    }
}
