package org.example.basic.oj.leetcode.Q36;

/**
 * @author anyuan
 * @since 2021-09-17 09:32
 */
class Solution_2d_array {
    /**
     * 对于每个位置的值，进行三种判断：所在行、所在列、所在3x3分区
     * <p>
     * 点[x,y]所在的格子是x/3*3+y/3
     *
     * @param board
     * @return
     */
    public boolean isValidSudoku(char[][] board) {
        // 直接用数组进行标记，用三个9*9的数组
        // 下标0~8对应值1~9
        // false表示未有过这个值
        // 如果发现某个新的位置的值是true了，说明这个值之前填过了，即说明该数组不合规
        // 可以优化，改为使用二进制去标识该位是否有放过值
        boolean[][] rows = new boolean[9][9];
        boolean[][] columns = new boolean[9][9];
        boolean[][] areas = new boolean[9][9];

        for (int x = 0; x < 9; x++) {
            for (int y = 0; y < 9; y++) {
                if (board[x][y] == '.') {
                    continue;
                }
                int val = board[x][y] - '1';
                int areaNo = x / 3 * 3 + y / 3;
                if (rows[x][val] || columns[y][val] || areas[areaNo][val]) {
                    return false;
                }
                rows[x][val] = columns[y][val] = areas[areaNo][val] = true;
            }
        }
        return true;
    }

    public static void main(String[] args) {

    }
}
