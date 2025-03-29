package org.example.basic.oj.leetcode.Q2711;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

class Solution {
    public int[][] differenceOfDistinctValues(int[][] grid) {
        final int m = grid.length, n = grid[0].length;
        final Map<Integer,Integer> map = new HashMap<>();
        final Set<Integer> set = new HashSet<>();
        final int[][] result = new int[m][n];
        int startRow, startColumn;
        result[m-1][0] = 0;
        result[0][n-1] = 0;
        for (int b = -m+2; b < n-1; b++) {
            buildTimesMap(map, grid, b);
            if (b >= 0) {
                startRow = 0;
                startColumn = b;
            } else {
                startRow = -b;
                startColumn = 0;
            }
            int left;
            for (int i = startRow, j = i + b; i < m && j >= 0 && j < n; i++, j++) {
                left = set.size();
                int val = grid[i][j];
                set.add(val);
                int temp = map.get(val) - 1;
                if (0 == temp) {
                    map.remove(val);
                } else {
                    map.put(val, temp);
                }
                result[i][j] = Math.abs(map.size() - left);
            }
            map.clear();
            set.clear();
        }
        return result;
    }

    // 一个m*n的矩阵，一共有m+n-1条y=-x的对角线。这里假设从0,0开始的一条为第0条，向上的+1，向下的-1
    private void buildTimesMap(Map<Integer,Integer> map, int[][] grid, int b) {
        final int m = grid.length, n = grid[0].length;
        // 根据b算出起点位置
        // b=0 => 0,0, b=1 => 0,1, b=-1 => -1,0
        int startRow, startColumn;
        if (b >= 0) {
            startRow = 0;
            startColumn = b;
        } else {
            startRow = -b;
            startColumn = 0;
        }
        // 计算这一条对角线上所有值的出现次数
        for (int i = startRow, j = i + b; i < m && j >= 0 && j < n; i++, j++) {
            map.put(grid[i][j], map.getOrDefault(grid[i][j], 0) + 1);
        }
    }
}



