package org.example.basic.oj.leetcode.Q883;

class Solution {
    public int projectionArea(int[][] grid) {
        final int n = grid.length;
        int[] leftMax = new int[n+1], rightMax = new int[n+1];
        int bottom = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int num = grid[i][j];
                if (num != 0) {
                    bottom++;
                }
                leftMax[i] = Math.max(leftMax[i], num);
                rightMax[j] = Math.max(rightMax[j], num);
            }
        }
        int answer = bottom;
        for (int i = 0; i <= n; i++) {
            answer += leftMax[i];
            answer += rightMax[i];
        }
        return answer;
    }
}
