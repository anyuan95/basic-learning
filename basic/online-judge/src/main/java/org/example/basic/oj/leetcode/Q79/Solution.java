package org.example.basic.oj.leetcode.Q79;

/**
 * @author anyuan
 * @since 2021-09-14 21:44
 */
class Solution {
    /**
     * m == board.length
     * n = board[i].length
     * 1 <= m, n <= 6
     * 1 <= word.length <= 15
     * board 和 word 仅由大小写英文字母组成
     *
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        for (int x = 0; x < board.length; x++) {
            for (int y = 0; y < board[x].length; y++) {
                // bfs
                if (process(board, word, 0, x, y)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean process(char[][] board, String word, int currentIndex, int x, int y) {
        if (currentIndex >= word.length()) {
            return true;
        }
        // 越界的、找过的、false
        if (x < 0 || y < 0 || x >= board.length || y >= board[0].length || board[x][y] != word.charAt(currentIndex)) {
            return false;
        }
        // 需要有个记录数组，避免往回找
        // 参考评论区，在原数组上通过改值的方式进行记录。因为一定是大写字母，所以+256一定不是大写字母。
        board[x][y] += 256;
        final boolean exist = process(board, word, currentIndex + 1, x - 1, y)
                || process(board, word, currentIndex + 1, x + 1, y)
                || process(board, word, currentIndex + 1, x, y - 1)
                || process(board, word, currentIndex + 1, x, y + 1);
        board[x][y] -= 256;
        return exist;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.exist(new char[][]{
                {'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}
        }, "ABCCED"));
        System.out.println(solution.exist(new char[][]{
                {'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}
        }, "SEE"));
        System.out.println(solution.exist(new char[][]{
                {'A', 'B', 'C', 'E'}, {'S', 'F', 'C', 'S'}, {'A', 'D', 'E', 'E'}
        }, "ABCB"));
    }

}
