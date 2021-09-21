package org.example.basic.oj.leetcode.Q212;

import java.util.ArrayList;
import java.util.List;

/**
 * 暴力dfs竟然过了。。。不可思议
 *
 * @author anyuan
 * @since 2021-09-16 22:10
 */
class Solution {
    /**
     * 思路：和Q79一样，就是多了一层遍历。
     * 每次拿出一个词，从二维数组[0,0]位置开始进行dfs。如果找到了，就记录，然后找下一个词。
     *
     * 	1421 ms
     *
     * @param board
     * @param words
     * @return
     */
    public List<String> findWords(char[][] board, String[] words) {
        List<String> answer = new ArrayList<>();
        word_loop:
        for (String word : words) {
            for (int x = 0; x < board.length; x++) {
                for (int y = 0; y < board[x].length; y++) {
                    if (process(board, word, 0, x, y)) {
                        // 找到一个就可以
                        answer.add(word);
                        continue word_loop;
                    }
                }
            }
        }
        return answer;
    }

    private boolean process(char[][] board, String word, int currentIndex, int x, int y) {
        if (currentIndex >= word.length()) {
            return true;
        }
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || word.charAt(currentIndex) != board[x][y]) {
            return false;
        }
        board[x][y] += 256;
        // 上下左右
        boolean result = process(board, word, currentIndex + 1, x - 1, y)
                || process(board, word, currentIndex + 1, x + 1, y)
                || process(board, word, currentIndex + 1, x, y - 1)
                || process(board, word, currentIndex + 1, x, y + 1);
        board[x][y] -= 256;
        return result;
    }
}
