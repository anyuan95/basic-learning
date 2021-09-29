package org.example.basic.oj.leetcode.Q130;

/**
 * @author anyuan
 * @since 2021-09-27 16:54
 */
class Solution_0927 {
    public void solve(char[][] board) {
        // 把四周一圈上的0做dfs
        final int m = board.length, n = board[0].length;
        for (int x = 0; x < m; x++) {
            if (board[x][0] == 'O') {
                infect(board, x, 0);
            }
            if (board[x][n - 1] == 'O') {
                infect(board, x, n - 1);
            }
        }
        for (int y = 0; y < n; y++) {
            if (board[0][y] == 'O') {
                infect(board, 0, y);
            }
            if (board[m - 1][y] == 'O') {
                infect(board, m - 1, y);
            }
        }
        for (int x = 0; x < m; x++) {
            for (int y = 0; y < n; y++) {
                if (board[x][y] == 'O') {
                    board[x][y] = 'X';
                } else if (board[x][y] == 'Q') {
                    board[x][y] = 'O';
                }
            }
        }
    }

    private void infect(char[][] board, int x, int y) {
        if (x < 0 || x >= board.length || y < 0 || y >= board[0].length || board[x][y] != 'O') {
            return;
        }
        board[x][y] = 'Q';
        infect(board, x - 1, y);
        infect(board, x + 1, y);
        infect(board, x, y - 1);
        infect(board, x, y + 1);
    }
}
