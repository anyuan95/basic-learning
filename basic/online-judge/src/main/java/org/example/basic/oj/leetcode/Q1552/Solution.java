package org.example.basic.oj.leetcode.Q1552;

import java.util.Arrays;

/**
 * # 题目没看懂，先看了题解
 * 首先对数组进行排序；
 * 然后可证：在数组中的值对应的位置放置m（m>1）个球，（理论上的）最小间距为1，（理论上的）最大间距为(MaxPosition-MinPosition)/(m-1)；
 * 故，只要从1~(MaxPosition-MinPosition)/(m-1)中，根据position数组，找出最大的、满足position数组的gap即可
 */
class Solution {
    public int maxDistance(int[] position, int m) {
        Arrays.sort(position);
        int low = 1, high = (position[position.length - 1] - position[0]) / (m - 1);
        int ans = 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (check(position, mid, m)) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }

    private boolean check(int[] position, int gap, int m) {
        int count = 1, prePosition = position[0];
        for (int i = 1; i < position.length; i++) {
            if (position[i] - prePosition >= gap) {
                count++;
                prePosition = position[i];
            }
            if (count >= m) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] position = {5, 4, 3, 2, 1, 1000000000};
        int m = 2;
        System.out.println(new Solution().maxDistance(position, m));
    }

}