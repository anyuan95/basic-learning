package org.example.basic.oj.leetcode.Q74;

/**
 * @author anyuan
 * @since 2021-08-26 17:31
 */
class Solution {

    /**
     * 编写一个高效的算法来判断m x n矩阵中，是否存在一个目标值。该矩阵具有如下特性：
     * <p>
     * 每行中的整数从左到右按升序排列。
     * 每行的第一个整数大于前一行的最后一个整数。
     *
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length, columns = matrix[0].length;
        int left = 0, right = rows * columns - 1;
        while (left <= right) {
            int middle = left + ((right - left) >> 1);
            int row = middle / columns, column = middle % columns;
            if (matrix[row][column] == target) {
                return true;
            } else if (matrix[row][column] > target) {
                right = middle - 1;
            } else if (matrix[row][column] < target) {
                left = middle + 1;
            }
        }
        return false;
    }
}
