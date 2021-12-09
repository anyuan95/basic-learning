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
        // 考虑先后手的问题，X一定先手，即x一定大于等于o
        // 从另一方面考虑：
        // 如果X赢，则最后一手一定是X，x-o=1
        // 如果O赢，则最后一手一定是O，X=O

        // 首先判断是否X-O在0,1之间
        int xCount = 0, oCount = 0;
        char[][] chars = new char[3][3];
        for (int i = 0; i < board.length; i++) {
            chars[i] = board[i].toCharArray();
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (chars[i][j] == 'X') {
                    xCount++;
                } else if (chars[i][j] == 'O') {
                    oCount++;
                }
            }
        }
        if (xCount - oCount != 0 && xCount - oCount != 1) {
            return false;
        }

        // 然后判断，如果一方赢，是否对
        final boolean xWin = check(chars, 'X'), oWin = check(chars, 'O');
        if (xWin && xCount - oCount != 1) return false;
        if (oWin && xCount != oCount) return false;
        if (xWin && oWin) return false;
        return true;
    }

    private boolean check(char[][] chars, char c) {
        for (int i = 0; i < 3; i++) {
            if (chars[0][i] == c && chars[1][i] == c && chars[2][i] == c) return true;
            if (chars[i][0] == c && chars[i][1] == c && chars[i][2] == c) return true;
        }
        if ((chars[0][0] == c && chars[1][1] == c && chars[2][2] == c) || (chars[0][2] == c && chars[1][1] == c && chars[2][0] == c)) {
            return true;
        }
        return false;
    }
}
