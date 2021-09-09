package org.example.basic.oj.leetcode.Q130;

/**
 * 给定mxn的矩阵，所有字符都是X或O，
 * 找到所有被X围绕的O，把这些O变成X。
 *
 * @author anyuan
 * @since 2021-09-09 10:34
 */
class Solution_dfs_infect {
    /**
     * 思路：感染方式
     * 1.将边界的所有O及相连的O都感染成M
     * 2.将整个数组中的O都感染成X
     * 3.将所有M回退成O
     *
     *
     * 感染方式实际上就是DFS
     *
     * @param board
     */
    public void solve(char[][] board) {
        final int m = board.length, n = board[0].length;
        if (m <= 2 || n <= 2) {
            // 如果只有1/2行或者只有1/2列，是不可能被围绕的
            // 因为题目说明了，'被围绕的区间不会存在于边界上'
            return;
        }

        for (int x = 0; x < m; x++) {
            // 遍历边界：左边界
            infect(board, x, 0);
            // 遍历边界：右边界
            infect(board, x, n-1);
        }
        for (int y = 1; y < n-1; y++) {
            // 遍历边界：上边界（不包括左上和右上顶点）
            infect(board, 0, y);
            // 遍历边界：下边界（不包括左下和右下顶点）
            infect(board, m-1, y);
        }
        /*-----现在所有从边界开始的O节点都变成M了-----*/
        // 现在，board中剩下的所有O就都是被包围的，将这些O感染成X
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (board[x][y] == 'O') {
                    board[x][y] = 'X';
                }
            }
        }
        // 将所有用M标记的节点回退成O
        // 其实这个循环可以和上一个合并，不过时间复杂度并不会变
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (board[x][y] == 'M') {
                    board[x][y] = 'O';
                }
            }
        }
    }

    /**
     * 只处理外围的所有O及关联的所有O
     *
     * @param board
     * @param x
     * @param y
     */
    private void infect(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'O') {
            return;
        }
        // 感染成M
        board[x][y] = 'M';
        infect(board, x - 1, y);
        infect(board, x + 1, y);
        infect(board, x, y - 1);
        infect(board, x, y + 1);
    }

}
