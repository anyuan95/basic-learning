package org.example.basic.oj.leetcode.Q498;

import java.util.ArrayList;
import java.util.List;

class Solution {
    public int[] findDiagonalOrder(int[][] mat) {
        final List<Integer> answer = new ArrayList<>();
        final int m = mat.length, n = mat[0].length;
        int x = 0, y = 0, dir = 1;
        for (int i = 0; i < m * n; i++) {
            answer.add(mat[x][y]);
            int nx = x, ny = y;
            if (dir == 1) {
                nx = x - 1;
                ny = y + 1;
            } else {
                nx = x + 1;
                ny = y - 1;
            }
            if (nx < 0 || nx >= m || ny < 0 || ny >= n) {
                if (dir == 1) {
                    nx = y + 1 < n ? x : x + 1;
                    ny = y + 1 < n ? y + 1 : y;
                } else {
                    nx = x + 1 < m ? x + 1 : x;
                    ny = x + 1 < m ? y : y + 1;
                }
                dir *= -1;
            }
            x = nx;
            y = ny;
        }
        return answer.stream().mapToInt(Integer::intValue).toArray();
    }
}