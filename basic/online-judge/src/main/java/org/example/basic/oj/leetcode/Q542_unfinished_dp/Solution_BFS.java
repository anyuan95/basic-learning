package org.example.basic.oj.leetcode.Q542_unfinished_dp;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author anyuan
 * @since 2021-08-20 15:12
 */
class Solution_BFS {
    /**
     * 应该算是bfs
     *
     * @param mat
     * @return
     */
    public int[][] updateMatrix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] answer = new int[m][n];
        Queue<int[]> currentRoundPoints = new LinkedList<>();
        int oneCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (mat[i][j] == 0) {
                    currentRoundPoints.offer(new int[]{i, j});
                } else if (mat[i][j] == 1) {
                    oneCount++;
                }
            }
        }

        // 从每个0位置开始扩散
        int round = 0;
        while (oneCount > 0 && !currentRoundPoints.isEmpty()) {
            final int size = currentRoundPoints.size();
            for (int i = 0; i < size; i++) {
                int[] zeroPoint = currentRoundPoints.poll();
                final int x = zeroPoint[0], y = zeroPoint[1];
                // 当前节点一定是0
                if (answer[x][y] == 0) {
                    // answer==0表示没填充过
                    // 那就把它填上
                    answer[x][y] = round;
                }
                if (x - 1 >= 0 && mat[x - 1][y] == 1) {
                    mat[x - 1][y] = 0;
                    currentRoundPoints.add(new int[]{x - 1, y});
                }
                if (x + 1 < m && mat[x + 1][y] == 1) {
                    mat[x + 1][y] = 0;
                    currentRoundPoints.add(new int[]{x + 1, y});
                }
                if (y - 1 >= 0 && mat[x][y - 1] == 1) {
                    mat[x][y - 1] = 0;
                    currentRoundPoints.add(new int[]{x, y - 1});
                }
                if (y + 1 < n && mat[x][y + 1] == 1) {
                    mat[x][y + 1] = 0;
                    currentRoundPoints.add(new int[]{x, y + 1});
                }
            }
            round++;
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution_BFS solution = new Solution_BFS();
        int[][] nums = new int[][] {
//                {0,0,0},{0,1,0},{0,0,0}
                {0,0,0},{0,1,0},{1,1,1}
        };
        System.out.println(Arrays.deepToString(solution.updateMatrix(nums)));
    }
}
