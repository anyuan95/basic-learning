package org.example.basic.oj.leetcode.Q1380;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<Integer> luckyNumbers(int[][] matrix) {
        List<Integer> answer = new ArrayList<>();
        final int m = matrix.length, n = matrix[0].length;
        final int[] mMin = new int[m];
        for (int i = 0; i < m; i++) {
            int currentLineMinIndex = 0;
            for (int j = 0; j < n; j++) {
                if (matrix[i][currentLineMinIndex] > matrix[i][j]) {
                    currentLineMinIndex = j;
                }
            }
            mMin[i] = currentLineMinIndex;
        }
        // 拿到每行最小值了，再看这个数是不是所在列的最大值
        for (int i = 0; i < m; i++) {
            int currentRowMaxIndex = mMin[i];
            for (int j = 0; j < m; j++) {
                if (matrix[j][currentRowMaxIndex] > matrix[i][currentRowMaxIndex]) {
                    currentRowMaxIndex = -1;
                    break;
                }
            }
            if (currentRowMaxIndex != -1) {
                answer.add(matrix[i][currentRowMaxIndex]);
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.luckyNumbers(new int[][]{{3, 7, 8}, {9, 11, 13}, {15, 16, 17}}));
    }
}
