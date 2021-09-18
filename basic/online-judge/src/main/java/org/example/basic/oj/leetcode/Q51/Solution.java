package org.example.basic.oj.leetcode.Q51;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author anyuan
 * @since 2021-09-18 17:24
 */
class Solution {
    // 斜率为1的对角线。节点[x,y]在的对角线序号为x+y
    boolean[] diagonals1;
    // 斜率为-1的对角线。节点[x,y]在的对角线序号为y-x+n
    boolean[] diagonals2;
    // 行。行实际上用不到，因为实际上是逐行填的
    // 列
    boolean[] columns;

    public List<List<String>> solveNQueens(int n) {
        // 斜率为1的对角线
        diagonals1 = new boolean[2 * n - 1];
        // 斜率为-1的对角线
        diagonals2 = new boolean[2 * n - 1];
        // 列
        columns = new boolean[n];


        List<List<String>> answers = new ArrayList<>();
        final int[] position = new int[n];
        Arrays.fill(position, -1);
        process(answers, position, 0);
        return answers;
    }

    /**
     * @param answers
     * @param position 第i行皇后的位置为position[i]，长度即为皇后总数
     * @param x        also as rowNum 第几个皇后，也可以认为是第几行。表示当前要往第几行放值
     */
    private void process(List<List<String>> answers, int[] position, int x) {
        if (x == position.length) {
            answers.add(cast(position));
            return;
        }
        final int n = position.length;
        // 过滤出可以放置的列序号
        for (int y = 0; y < n; y++) {
            // 该点的：正对角线没有值、负对角线没有值、所在列没有值，则可以试着填一下
            if (!diagonals1[x + y] && !diagonals2[y - x + n - 1] && !columns[y]) {
                // 标记bool数组
                diagonals1[x + y] = true;
                diagonals2[y - x + n - 1] = true;
                columns[y] = true;
                position[x] = y;

                process(answers, position, x + 1);

                // 遍历完，恢复现场
                diagonals1[x + y] = false;
                diagonals2[y - x + n - 1] = false;
                columns[y] = false;
                position[x] = -1;
            }
        }
    }

    @Deprecated
    private boolean inSameDiagonal(int x1, int y1, int x2, int y2) {
        // 对于任意两个点，只要横坐标之差等于纵坐标之差，就可以认为是在同一条对角线删
        return Math.abs(x1 - x2) == Math.abs(y1 - y2);
    }

    private List<String> cast(int[] position) {
        List<String> answer = new ArrayList<>();
        final int n = position.length;
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < n; j++) {
                sb.append(j == position[i] ? 'Q' : '.');
            }
            answer.add(sb.toString());
        }
        return answer;
    }

    public static void main(String[] args) {
        final Solution solution = new Solution();
        System.out.println(solution.solveNQueens(4));
        System.out.println(solution.solveNQueens(1));
        final List<List<String>> queen8 = solution.solveNQueens(8);
        System.out.println(queen8);
        System.out.println(queen8.size());
    }


}
