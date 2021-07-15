package org.example.foo;

/**
 * @author anyuan
 * @since 2021-05-12 17:41
 */
public class NQueen {

    public static void main(String[] args) {
        /*
        * 算法如下：先尝试将第k个皇后摆放在棋盘的第k行第一列，
        * 然后判断该位置是否与已经放置的皇后有冲突，
        * 如果没有冲突，则继续放置下一个皇后；
        * 如果有冲突，则将皇后向后移动一列到第二列，
        * 重复进行校验。
        * 当放置到最后一列的时候仍旧没有满足条件的位置，
        * 则重新放置上一个皇后的位置。如此反复直到得到一个满足条件的解。
        * */

    }

    private static class GridInfo {

    }

    public boolean haveConflict(int[][] board, int row, int column) {

        return false;
    }
}
