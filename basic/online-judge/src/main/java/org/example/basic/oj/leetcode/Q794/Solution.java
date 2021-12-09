package org.example.basic.oj.leetcode.Q794;

/**
 * @author anyuan
 * @date 2021-12-09 22:38
 */
class Solution {
    /**
     * 玩家轮流将字符放入空位（' '）中。
     * 玩家 1 总是放字符 'X' ，而玩家 2 总是放字符 'O' 。
     * 'X' 和 'O' 只允许放置在空位中，不允许对已放有字符的位置进行填充。
     * 当有 3 个相同（且非空）的字符填充任何行、列或对角线时，游戏结束。
     * 当所有位置非空时，也算为游戏结束。
     * 如果游戏结束，玩家不允许再放置字符。
     *
     * @param board
     * @return
     */
    public boolean validTicTacToe(String[] board) {
        // 考虑无效的几种情况
        // 1.初始状态已经有一方胜出
        // 2.不满足1的前提下，格子已经满了
        // 3.两种字符数量相差大于1
        int xCount = 0, oCount = 0;
        char[][] chars = new char[3][3];
        for (int i = 0; i < board.length; i++) {
            chars[i] = board[i].toCharArray();
        }
        for (int i = 0; i < 3; i++) {
            if (chars[i][0] == chars[i][1] && chars[i][1] == chars[i][2]) {
                return false;
            }
        }
        for (int j = 0; j < 3; j++) {
            if (chars[0][j] == chars[1][j] && chars[1][j] == chars[2][j]) {
                return false;
            }
        }
        if ((chars[0][0] == chars[1][1] && chars[1][1] == chars[2][2]) || (chars[0][0] == chars[1][1] && chars[1][1] == chars[2][2])) {

        }



    }
}
