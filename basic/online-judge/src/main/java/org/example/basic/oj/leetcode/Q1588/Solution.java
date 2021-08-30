package org.example.basic.oj.leetcode.Q1588;

/**
 * 求给定数组中所有长度为奇数的连续子数组的元素和
 *
 * @author anyuan
 * @since 2021-08-30 09:03
 */
class Solution {

    public int sumOddLengthSubarrays(int[] arr) {
        // 计算得到有多少个长度为奇数的连续子数组中包含元素i
        int answer = 0;
        final int n = arr.length;
        for (int i = 0; i < n; i++) {
            // 若要i在奇数长度连续子数组中出现
            // 那么该子数组，去掉i以外的元素个数一定是偶数
            // 那么i左侧和右侧一定同时有奇数个元素，或同时有偶数个元素
            // 左右同时有奇数个元素的次数 = 左侧奇数元素次数 * 右侧奇数元素次数
            // 偶数同理
            int leftOdd = (i + 1) / 2, rightOdd = (n - i) / 2, leftEven = i / 2 + 1, rightEven = (n - i + 1) / 2;
            answer += arr[i] * (leftOdd * rightOdd + leftEven * rightEven);
        }
        return answer;
    }
}
