package org.example.basic.oj.leetcode.Q1601;

import java.util.Arrays;

class Solution {
    /**
     * 无脑穷举吧
     * 按照位运算的方式，反正最大值也就16位
     * <p>
     * 1 <= n <= 20
     * 1 <= requests.length <= 16
     *
     * @param n
     * @param requests
     * @return
     */
    public int maximumRequests(int n, int[][] requests) {
        final int len = requests.length;
        // 遍历的最大值
        int max = (1 << len) - 1;
        // 所有楼的情况
        int[] height = new int[n];
        int answer = 0;

        for (int i = max; i > 0; i--) {
            int digit = digitCount(i);
            if (digit > answer && isOk(height, i, requests)) {
                answer = digit;
            }
        }
        return answer;
    }

    private int digitCount(int bin) {
        int answer = 0;
        while (bin != 0) {
            if ((bin & 1) == 1) {
                answer++;
            }
            bin >>= 1;
        }
        return answer;
    }

    private boolean isOk(int[] height, int bin, int[][] requests) {
        Arrays.fill(height, 0);
        final int len = requests.length;
        for (int i = 0; i < len; i++) {
            if ((bin & 1) == 1) {
                height[requests[i][0]]--;
                height[requests[i][1]]++;
            }
            bin >>= 1;
        }
        for (int i : height) {
            if (i != 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
//        System.out.println(solution.maximumRequests(5, new int[][]{{0, 1}, {1, 0}, {0, 1}, {1, 2}, {2, 0}, {3, 4}}));
//        System.out.println(solution.maximumRequests(3, new int[][]{{1, 2}, {1, 2}, {2, 2}, {0, 2}, {2, 1}, {1, 1}, {1, 2}}));
        System.out.println(solution.maximumRequests(3, new int[][]{{1, 2}, {2, 2}, {0, 0}, {1, 1}, {0, 2}, {0, 0}, {2, 1}, {0, 1}, {1, 0}, {2, 2}, {0, 1}, {2, 0}, {2, 2}}));

//        {1, 2}, {2, 2}, {0, 0}, {1, 1}, , {0, 0}, , {0, 1}, {1, 0}, {2, 2}, {0, 1}, {2, 0}, {2, 2}
    }
}
