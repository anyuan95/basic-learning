package org.example.basic.oj.leetcode.Q74;

/**
 * @author anyuan
 * @since 2021-09-17 11:13
 */
class Solution_0917 {
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length, columns = matrix[0].length;
        final int numCount = rows * columns;
        int left = 0, right = numCount - 1, middle;
        while (left <= right) {
            middle = left + ((right - left) >> 1);
            final int val = matrix[middle / columns][middle % columns];
            if (val == target) {
                return true;
            } else if (val < target) {
                left = middle + 1;
            } else {
                right = middle - 1;
            }
        }
        return false;
    }
}
