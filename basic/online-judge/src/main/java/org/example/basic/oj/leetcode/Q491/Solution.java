package org.example.basic.oj.leetcode.Q491;

/**
 * @author anyuan
 * @date 2021-12-18 22:47
 */
class Solution {
    /**
     * 给你一个大小为 m x n 的矩阵 board 表示甲板，其中，每个单元格可以是一艘战舰 'X' 或者是一个空位 '.' ，返回在甲板 board 上放置的 战舰 的数量。
     *
     * 战舰 只能水平或者垂直放置在 board 上。换句话说，战舰只能按 1 x k（1 行，k 列）或 k x 1（k 行，1 列）的形状建造，其中 k 可以是任意大小。
     * 两艘战舰之间至少有一个水平或垂直的空位分隔 （即没有相邻的战舰）。
     *
     * @param board
     * @return
     */
    public int countBattleships(char[][] board) {
        // 实际上就是个dfs或者bfs

        // 猜想：题目给定的规则是，不会有相邻的船。所以仅当一个点的右和下都是空或者墙时，才可以认定是一个船？
        int bsCount = 0;
        final int m = board.length, n = board[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i][j] == 'X' && (i+1 == m || board[i+1][j] == '.') && (j+1 == n || board[i][j+1] == '.')) {
                    bsCount++;
                }
            }
        }
        return bsCount;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.countBattleships(new char[][]{{'X', '.', '.', 'X'}, {'.', '.', '.', 'X'}, {'.', '.', '.', 'X'}}));
    }
}
